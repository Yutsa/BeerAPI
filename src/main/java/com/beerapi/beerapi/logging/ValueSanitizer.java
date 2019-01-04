package com.beerapi.beerapi.logging;

import java.util.regex.Pattern;

public final class ValueSanitizer
{

  private static final Pattern NEWLINE_PATTERN = Pattern.compile("[\r\n]");

  private ValueSanitizer(){}
  public static String sanitizeInput(Object object) {
    if (object == null)
    {
      return "";
    }
    return NEWLINE_PATTERN.matcher(object.toString()).replaceAll("");
  }
}
