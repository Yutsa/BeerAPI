package com.beerapi.beerapi.repository;

import com.beerapi.beerapi.model.entities.Beer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long>
{
  Iterable<Beer> findBeerByAlcoholPercentage(double alcoholPercentage);
  Iterable<Beer> findBeerByNameIgnoreCaseContaining(String name);
}
