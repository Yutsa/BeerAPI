package com.beerapi.beerapi.controller;

import com.beerapi.beerapi.exception.BeerNotFoundException;
import com.beerapi.beerapi.model.dto.BeerDTO;
import com.beerapi.beerapi.model.entities.Beer;
import com.beerapi.beerapi.repository.BeerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/beers")
public class BeerController implements BeerAPI
{
  private BeerRepository beerRepository;
  private static final Logger logger = LoggerFactory.getLogger(BeerController.class);

  public BeerController(BeerRepository beerRepository)
  {
    this.beerRepository = beerRepository;
  }

  @Override
  @GetMapping("/")
  public Iterable<BeerDTO> getAllBeers()
  {
    logger.info("Getting all the beers.");
    return StreamSupport.stream(beerRepository.findAll().spliterator(), false)
        .map(BeerDTO::new)
        .collect(Collectors.toList());
  }

  @Override
  @GetMapping("/{id}")
  public BeerDTO getById(@PathVariable Long id) {
    logger.info("Getting beer from id {}", id);
    Optional<Beer> beer = beerRepository.findById(id);

    if (!beer.isPresent())
    {
      String message = MessageFormat.format("Beer with id {0} not found", id);
      logger.warn(message);
      throw new BeerNotFoundException(message);
    }

    return new BeerDTO(beer.get());
  }

  @Override
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    logger.info("Deleting beer with id {}", id);
    beerRepository.deleteById(id);
  }

  @Override
  @PostMapping("/beers")
  public BeerDTO addBeer(@RequestBody BeerDTO newBeer) {
    logger.info("Adding beer {}", newBeer);
    Beer beer = new Beer(newBeer);
    return new BeerDTO(beerRepository.save(beer));
  }

  @Override
  @GetMapping("/beers/similar/{id}")
  public Iterable<BeerDTO> getSimilarBeers(@PathVariable Long id)
  {
    logger.info("Getting beers similar to the beer with id {}", id);
    Optional<Beer> initialBeer = beerRepository.findById(id);

    if (!initialBeer.isPresent())
    {
      String message = MessageFormat.format("Beer with id {0} not found", id);
      logger.warn(message);
      throw new BeerNotFoundException(message);
    }

    return StreamSupport.stream(beerRepository.
        findBeerByAlcoholPercentage(initialBeer.get().getAlcoholPercentage()).spliterator(),
        false)
        .map(BeerDTO::new)
        .collect(Collectors.toList());

  }
}
