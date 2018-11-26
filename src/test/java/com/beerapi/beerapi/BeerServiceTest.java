package com.beerapi.beerapi;

import com.beerapi.beerapi.services.BeerService;

import org.junit.Assert;
import org.junit.Test;

public class BeerServiceTest
{
  private BeerService beerService;

  public BeerServiceTest()
  {
    beerService = new BeerService(null);
  }

  @Test
  public void prepareSearchQuery_ShouldReplaceOe() {
    Assert.assertEquals("oeuf", beerService.prepareSearchQuery("œuf"));
  }
}
