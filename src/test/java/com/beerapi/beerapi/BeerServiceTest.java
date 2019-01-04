package com.beerapi.beerapi;

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
  
  @Test
  public void similarBeer_UseCorrectPercentage() {
    BeerRepository mockBeerRepository = mock(BeerRepository.class);
    when(mockBeerRepository.findById(3L))
        .thenReturn(Optional.of(new Beer(12, "TestBeer")));

    BeerService beerService = new BeerService(mockBeerRepository);

    beerService.getSimilarBeer(3L);
    verify(mockBeerRepository).findBeerByAlcoholPercentage(12);
  }

  @Test(expected = BeerNotFoundException.class)
  public void similarBeer_BeerNotFound() {
    BeerRepository mockBeerRepository = mock(BeerRepository.class);
    when(mockBeerRepository.findById(3L)).thenReturn(Optional.empty());
    BeerService beerService = new BeerService(mockBeerRepository);

    beerService.getSimilarBeer(3L);
  }
}
