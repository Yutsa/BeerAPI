package com.beerapi.beerapi.controller;

import com.beerapi.beerapi.model.resources.BeerResource;

import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/beers")
public interface BeerAPI
{
  @ApiOperation(value = "Retrieve all the beers", nickname = "Retrieve all the beers",
      tags={ "Beers" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success") })
  @GetMapping("/")
  Resources<BeerResource> getAllBeers();

  @ApiOperation(value = "Retrieve a beer by its ID", nickname = "Retrieve a beer by ID",
      tags={ "Beers" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 404, message = "Beer with id {id} not found") })
  @GetMapping("/{id}")
  BeerResource getById(Long id);

  @ApiOperation(value = "Deletes a beer by its ID", nickname = "Delete a beer by ID",
      tags={ "Beers" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 404, message = "Beer with id {id} not found") })
  @DeleteMapping("/{id}")
  void deleteById(Long id);

  @ApiOperation(value = "Adds a beer", nickname = "Adds a beer", tags={ "Beers" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 404, message = "Beer with id {id} not found") })
  @PostMapping("/")
  BeerResource addBeer(BeerResource newBeer);

  @ApiOperation(value = "Retrieve the beers that are like the one passed as parameter",
      nickname = "Similar Beers", tags={ "Beers"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success")})
  @GetMapping("/similar/{id}")
  Iterable<BeerResource> getSimilarBeers(Long id);

  @ApiOperation(value = "Search for a beer with its name or a part of its name.",
      nickname = "Search Beer", tags={"Beers"})
  @GetMapping("/search/{query}")
  Iterable<BeerResource> searchBeer(String query);
}
