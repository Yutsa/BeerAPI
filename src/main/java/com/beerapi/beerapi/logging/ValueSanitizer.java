package com.beerapi.beerapi.logging;

public final class ValueSanitizer
{

  public static final String NEWLINE_PATTERN = "[\r\n]";

  private ValueSanitizer(){}
  public static String sanitizeInput(Object object) {
    return object.toString().replaceAll(NEWLINE_PATTERN,"");
  }
}
