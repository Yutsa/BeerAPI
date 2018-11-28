package com.beerapi.beerapi.controller;

import com.beerapi.beerapi.model.entities.Beer;
import com.beerapi.beerapi.model.resources.BeerResource;
import com.beerapi.beerapi.model.resources.BeerResourceAssembler;
import com.beerapi.beerapi.services.BeerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class BeerController implements BeerAPI
{
  private BeerResourceAssembler assembler;
  private BeerService beerService;
  private static final Logger logger = LoggerFactory.getLogger(BeerController.class);

  public BeerController(BeerResourceAssembler assembler,
                        BeerService beerService)
  {
    this.assembler = assembler;
    this.beerService = beerService;
  }

  @Override
  public Resources<BeerResource> getAllBeers()
  {
    logger.info("Getting all the beers.");
    List<BeerResource> beers = assembler.toResources(beerService.getAllBeers());

    Link link = linkTo(BeerController.class).withSelfRel();
    return new Resources<>(beers, link);
  }

  @Override
  public BeerResource getById(@PathVariable Long id) {
    logger.info("Getting beer from id {}.", id);
    return assembler.toResource(beerService.getById(id));
  }

  @Override
  public void deleteById(@PathVariable Long id) {
    logger.info("Deleting beer with id {}.", id);
    beerService.deleteById(id);
  }

  @Override
  public BeerResource addBeer(@RequestBody BeerResource newBeer) {
    logger.info("Adding beer {}.", newBeer);
    return assembler.toResource(beerService.addBeer(newBeer));
  }

  @Override
  public Resources<BeerResource> getSimilarBeers(@PathVariable Long id)
  {
    Iterable<Beer> similarBeers = beerService.getSimilarBeer(id);
    List<BeerResource> beers = assembler.toResources(similarBeers);

    Link link = linkTo(BeerController.class).withSelfRel();
    return new Resources<>(beers, link);
  }

  @Override
  public Iterable<BeerResource> searchBeer(@PathVariable String query)
  {
    return assembler.toResources(beerService.searchBeer(query));
  }
}
