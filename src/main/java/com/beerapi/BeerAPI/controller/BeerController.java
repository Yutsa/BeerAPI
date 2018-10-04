package com.beerapi.BeerAPI.controller;

import com.beerapi.BeerAPI.exception.BeerNotFoundException;
import com.beerapi.BeerAPI.model.Beer;
import com.beerapi.BeerAPI.repository.BeerRepository;

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
  public Iterable<Beer> getAllBeers()
  {
    return beerRepository.findAll();
  }

  @Override
  @GetMapping("/{id}")
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

  @Override
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    logger.info("Deleting beer with id {}", id);
    beerRepository.deleteById(id);
  }

  @Override
  @PostMapping("/beers")
  public Beer addBeer(@RequestBody Beer newBeer) {
    return beerRepository.save(newBeer);
  }
}
