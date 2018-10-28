package com.beerapi.beerapi.controller;

import com.beerapi.beerapi.exception.BeerNotFoundException;
import com.beerapi.beerapi.model.entities.Beer;
import com.beerapi.beerapi.model.resources.BeerResource;
import com.beerapi.beerapi.model.resources.BeerResourceAssembler;
import com.beerapi.beerapi.repository.BeerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/beers")
public class BeerController implements BeerAPI
{
  private BeerRepository beerRepository;
  private BeerResourceAssembler assembler;
  private static final Logger logger = LoggerFactory.getLogger(BeerController.class);

  public BeerController(BeerRepository beerRepository, BeerResourceAssembler assembler)
  {
    this.beerRepository = beerRepository;
    this.assembler = assembler;
  }

  @Override
  @GetMapping("/")
  public Resources<BeerResource> getAllBeers()
  {
    logger.info("Getting all the beers.");
    List<BeerResource> beers = assembler.toResources(beerRepository.findAll());

    Link link = linkTo(BeerController.class).withSelfRel();
    return new Resources<>(beers, link);
  }

  @Override
  @GetMapping("/{id}")
  public BeerResource getById(@PathVariable Long id) {
    logger.info("Getting beer from id {}", id);
    Optional<Beer> beer = beerRepository.findById(id);

    if (!beer.isPresent())
    {
      String message = MessageFormat.format("Beer with id {0} not found", id);
      logger.warn(message);
      throw new BeerNotFoundException(message);
    }

    return assembler.toResource(beer.get());
  }

  @Override
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    logger.info("Deleting beer with id {}", id);
    beerRepository.deleteById(id);
  }

  @Override
  @PostMapping("/beers")
  public BeerResource addBeer(@RequestBody BeerResource newBeer) {
    logger.info("Adding beer {}", newBeer);
    Beer beer = new Beer(newBeer);
    beerRepository.save(beer);
    return assembler.toResource(beer);
  }

  @Override
  @GetMapping("/beers/similar/{id}")
  public Resources<BeerResource> getSimilarBeers(@PathVariable Long id)
  {
    logger.info("Getting beers similar to the beer with id {}", id);
    Optional<Beer> initialBeer = beerRepository.findById(id);

    if (!initialBeer.isPresent())
    {
      String message = MessageFormat.format("Beer with id {0} not found", id);
      logger.warn(message);
      throw new BeerNotFoundException(message);
    }

    List<BeerResource> beers = assembler.toResources(beerRepository.
        findBeerByAlcoholPercentage(initialBeer.get().getAlcoholPercentage()));

    Link link = linkTo(BeerController.class).withSelfRel();
    return new Resources<>(beers, link);

  }
}
