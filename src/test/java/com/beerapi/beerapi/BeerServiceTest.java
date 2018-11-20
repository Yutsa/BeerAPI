package com.beerapi.beerapi;

import com.beerapi.beerapi.repository.BeerRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeerServiceTest
{

  @Test
  public void getSimilarBeerTest() {
    BeerRepository mockRepository = mock(BeerRepository.class);
  }
}
