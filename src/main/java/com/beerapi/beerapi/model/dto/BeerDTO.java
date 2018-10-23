package com.beerapi.beerapi.model.dto;

import com.beerapi.beerapi.model.entities.Beer;

import java.util.Objects;


public class BeerDTO
{
  private Long id;
  private String name;
  private double alcoholPercentage;

  public BeerDTO(double alcoholPercentage, String name)
  {
    this.name = name;
    this.alcoholPercentage = alcoholPercentage;
  }

  public BeerDTO(Beer beer)
  {
    this.name = beer.getName();
    this.alcoholPercentage = beer.getAlcoholPercentage();
    this.id = beer.getId();
  }

  public BeerDTO() {}

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public double getAlcoholPercentage()
  {
    return alcoholPercentage;
  }

  public void setAlcoholPercentage(double alcoholPercentage)
  {
    this.alcoholPercentage = alcoholPercentage;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  @Override
  public String toString()
  {
    return "BeerDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", alcoholPercentage=" + alcoholPercentage +
        '}';
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BeerDTO beerDTO = (BeerDTO) o;
    return Double.compare(beerDTO.alcoholPercentage, alcoholPercentage) == 0 &&
        Objects.equals(id, beerDTO.id) &&
        Objects.equals(name, beerDTO.name);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, name, alcoholPercentage);
  }
}
