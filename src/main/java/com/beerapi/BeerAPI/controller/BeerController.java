package com.beerapi.BeerAPI.controller;

import com.beerapi.BeerAPI.exception.BeerNotFoundException;
import com.beerapi.BeerAPI.model.Beer;
import com.beerapi.BeerAPI.repository.BeerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
public class BeerController
{
  BeerRepository beerRepository;
  private static final Logger logger = LoggerFactory.getLogger(BeerController.class);

  public BeerController(BeerRepository beerRepository)
  {
    this.beerRepository = beerRepository;
  }

  @GetMapping("/version")
  public Map<String, String> getVersion() {
    return Collections.singletonMap("version", "0.0.1-SNAPSHOT");
  }

  @GetMapping("/beers")
  public Iterable<Beer> getAllBeers()
  {
    return beerRepository.findAll();
  }

  @GetMapping("/beers/{id}")
  public Beer getById(@PathVariable Long id) {
    logger.info("Getting beer from id {}", id);
    Optional<Beer> beer = beerRepository.findById(id);

    if (!beer.isPresent())
    {
      String message = MessageFormat.format("Beer with id {0} not found", id);
      throw new BeerNotFoundException(message);
    }

    return beer.get();
  }
}
