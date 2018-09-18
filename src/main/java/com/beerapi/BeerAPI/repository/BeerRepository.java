package com.beerapi.BeerAPI.repository;

import com.beerapi.BeerAPI.model.Beer;

import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<Beer, Long>
{
}
