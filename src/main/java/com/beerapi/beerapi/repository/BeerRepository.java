package com.beerapi.beerapi.repository;

import com.beerapi.beerapi.model.entities.Beer;

import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<Beer, Long>
{
  Iterable<Beer> findBeerByAlcoholPercentage(double alcoholPercentage);
  Iterable<Beer> findBeerByName(String name);
}
