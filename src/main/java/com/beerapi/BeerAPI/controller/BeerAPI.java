package com.beerapi.BeerAPI.controller;

import com.beerapi.BeerAPI.model.Beer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface BeerAPI
{
  @ApiOperation(value = "Retrieve all the beers", nickname = "getAllBeers", tags={ "Beers", })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 404, message = "Beer with id {id} not found") })
  Iterable<Beer> getAllBeers();

  Beer getById(@PathVariable Long id);

  void deleteById(@PathVariable Long id);

  Beer addBeer(@RequestBody Beer newBeer);
}
