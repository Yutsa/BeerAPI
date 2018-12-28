package com.beerapi.beerapi.controller;

import com.beerapi.beerapi.logging.ValueSanitizer;
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
  private static final Logger LOGGER = LoggerFactory.getLogger(BeerController.class);

  public BeerController(final BeerResourceAssembler assembler,
                        final BeerService beerService)
  {
    this.assembler = assembler;
    this.beerService = beerService;
  }

  @Override
  public Resources<BeerResource> getAllBeers()
  {
    LOGGER.info("Getting all the beers.");
    List<BeerResource> beers = assembler.toResources(beerService.getAllBeers());

    Link link = linkTo(BeerController.class).withSelfRel();
    return new Resources<>(beers, link);
  }

  @Override
  public BeerResource getById(@PathVariable final Long id) {
    if (LOGGER.isInfoEnabled())
    {
      LOGGER.info("Getting beer from id {}.", ValueSanitizer.sanitizeInput(id));
    }
    return assembler.toResource(beerService.getById(id));
  }

  @Override
  public void deleteById(@PathVariable final Long id) {
    if (LOGGER.isInfoEnabled())
    {
      LOGGER.info("Deleting beer with id {}.", ValueSanitizer.sanitizeInput(id));
    }
    beerService.deleteById(id);
  }

  @Override
  public BeerResource addBeer(@RequestBody final BeerResource newBeer) {
    if (LOGGER.isInfoEnabled())
    {
      LOGGER.info("Adding beer {}.", ValueSanitizer.sanitizeInput(newBeer));
    }
    return assembler.toResource(beerService.addBeer(newBeer));
  }

  @Override
  public Resources<BeerResource> getSimilarBeers(@PathVariable final Long id)
  {
    List<BeerResource> beers = assembler.toResources(beerService.getSimilarBeer(id));

    Link link = linkTo(BeerController.class).withSelfRel();
    return new Resources<>(beers, link);
  }

  @Override
  public Iterable<BeerResource> searchBeer(@PathVariable final String query)
  {
    List<BeerResource> beers = assembler.toResources(beerService.searchBeer(query));

    Link link = linkTo(BeerController.class).withSelfRel();
    return new Resources<>(beers, link);
  }
}
