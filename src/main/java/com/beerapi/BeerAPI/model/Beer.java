package com.beerapi.BeerAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beers")
public class Beer
{
  @Id
  @GeneratedValue
  private long id;

  private String name;

  @Column(name = "abv")
  private double alcoholPercentage;

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

  public long getId()
  {
    return id;
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


}
