package com.beerapi.beerapi.logging;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValueSanitizerTest
{

  @Test
  public void testSanitizeInput()
  {
    Assert.assertEquals("Replace \\r\\n by empty character",
        "HelloWorld", ValueSanitizer.sanitizeInput("Hello\r\nWorld"));
  }
}