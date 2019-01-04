package com.beerapi.beerapi.service;

import com.beerapi.beerapi.exception.BeerNotFoundException;
import com.beerapi.beerapi.model.entities.Beer;
import com.beerapi.beerapi.repository.BeerRepository;
import com.beerapi.beerapi.services.BeerService;

import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BeerServiceTest
{
  @Test
  public void testSearchBeerWithOe() {
    BeerRepository mockBeerRepository = mock(BeerRepository.class);
    BeerService beerService = new BeerService(mockBeerRepository);

    beerService.searchBeer("œuf");
    verify(mockBeerRepository).findBeerByNameIgnoreCaseContaining("oeuf");
  }

  @Test
  public void testSearchBeerNullQuery() {
    BeerRepository mockBeerRepository = mock(BeerRepository.class);
    BeerService beerService = new BeerService(mockBeerRepository);

    beerService.searchBeer(null);
    verify(mockBeerRepository).findBeerByNameIgnoreCaseContaining("");
  }
  
  @Test
  public void testSimilarBeerUseCorrectPercentage() {
    BeerRepository mockBeerRepository = mock(BeerRepository.class);
    when(mockBeerRepository.findById(3L))
        .thenReturn(Optional.of(new Beer(12, "TestBeer")));

    BeerService beerService = new BeerService(mockBeerRepository);

    beerService.getSimilarBeer(3L);
    verify(mockBeerRepository).findBeerByAlcoholPercentage(12);
  }

  @Test(expected = BeerNotFoundException.class)
  public void testSimilarBeerBeerNotFound() {
    BeerRepository mockBeerRepository = mock(BeerRepository.class);
    when(mockBeerRepository.findById(3L)).thenReturn(Optional.empty());
    BeerService beerService = new BeerService(mockBeerRepository);

    beerService.getSimilarBeer(3L);
  }
}
