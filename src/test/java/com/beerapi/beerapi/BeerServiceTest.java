package com.beerapi.beerapi;

import com.beerapi.beerapi.repository.BeerRepository;
import com.beerapi.beerapi.services.BeerService;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BeerServiceTest
{
  @Test
  public void searchBeer_With_oe() {
    BeerRepository mockBeerRepository = mock(BeerRepository.class);
    BeerService beerService = new BeerService(mockBeerRepository);

    beerService.searchBeer("œuf");
    verify(mockBeerRepository).findBeerByNameIgnoreCaseContaining("oeuf");
  }

  @Test
  public void searchBeer_NullQuery() {
    BeerRepository mockBeerRepository = mock(BeerRepository.class);
    BeerService beerService = new BeerService(mockBeerRepository);

    beerService.searchBeer(null);
    verify(mockBeerRepository).findBeerByNameIgnoreCaseContaining("");
  }
}
