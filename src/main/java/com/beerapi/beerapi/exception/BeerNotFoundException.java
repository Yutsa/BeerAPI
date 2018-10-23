package com.beerapi.beerapi.exception;

public class BeerNotFoundException extends RuntimeException
{

  public BeerNotFoundException(String s)
  {
    super(s);
  }
}
