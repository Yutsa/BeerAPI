package com.beerapi.BeerAPI.exception;

public class BeerNotFoundException extends RuntimeException
{

  public BeerNotFoundException(String s)
  {
    super(s);
  }
}
