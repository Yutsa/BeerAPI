package com.beerapi.BeerAPI.controller;

import com.beerapi.BeerAPI.model.Beer;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface BeerAPI
{
  @ApiOperation(value = "Retrieve all the beers", nickname = "getAllBeers", tags={ "Beers" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 404, message = "Beer with id {id} not found") })
  Iterable<Beer> getAllBeers();

  Beer getById(Long id);

  void deleteById(Long id);

  Beer addBeer(Beer newBeer);

  @ApiOperation(value = "Retrieve the beers that are like the one passed as parameter", nickname = "Similar Beers", tags={ "Beers"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success")})
  Iterable<Beer> getSimilarBeers(Long id);
}
