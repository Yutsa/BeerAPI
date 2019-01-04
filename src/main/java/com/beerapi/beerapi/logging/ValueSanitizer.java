package com.beerapi.beerapi.logging;

public final class ValueSanitizer
{

  private static final String NEWLINE_PATTERN = "[\r\n]";

  private ValueSanitizer(){}
  public static String sanitizeInput(Object object) {
    if (object == null)
      return "";
    return object.toString().replaceAll(NEWLINE_PATTERN,"");
  }
}
