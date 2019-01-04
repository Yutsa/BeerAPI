package com.beerapi.beerapi.model.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

public class BeerResource extends ResourceSupport
{
  private String name;
  private double alcoholPercentage;

  @JsonCreator
  public BeerResource(@JsonProperty("alcoholPercentage") double alcoholPercentage,
                      @JsonProperty("name") String name)
  {
    this.name = name;
    this.alcoholPercentage = alcoholPercentage;
  }

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

  @ApiModelProperty(hidden = true)
  public void setLinks(final Link[] links)
  {
    super.add(links);
  }

  @Override
  public String toString()
  {
    return "BeerResource{" +
        ", name='" + name + '\'' +
        ", alcoholPercentage=" + alcoholPercentage +
        '}';
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeerResource beerResource = (BeerResource) o;
    return Double.compare(beerResource.alcoholPercentage, alcoholPercentage) == 0 &&
        Objects.equals(name, beerResource.name);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(name, alcoholPercentage);
  }
}
