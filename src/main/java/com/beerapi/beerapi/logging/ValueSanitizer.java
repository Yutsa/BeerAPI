package com.beerapi.beerapi.logging;

public class ValueSanitizer
{
  private ValueSanitizer(){}
  public static String sanitizeInput(Object object) {
    return object.toString().replaceAll("[\r\n]","");
  }
}
