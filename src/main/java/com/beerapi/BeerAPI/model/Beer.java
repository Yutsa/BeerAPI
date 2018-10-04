package com.beerapi.BeerAPI.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beers")
public class Beer
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "abv")
  private double alcoholPercentage;

  public Beer(double alcoholPercentage, String name)
  {
    this.name = name;
    this.alcoholPercentage = alcoholPercentage;
  }


  public Beer()
  {
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
    return "Beer{" +
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
    Beer beer = (Beer) o;
    return Double.compare(beer.alcoholPercentage, alcoholPercentage) == 0 &&
        Objects.equals(id, beer.id) &&
        Objects.equals(name, beer.name);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, name, alcoholPercentage);
  }
}
