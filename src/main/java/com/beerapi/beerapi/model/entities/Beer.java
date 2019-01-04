package com.beerapi.beerapi.model.entities;

import com.beerapi.beerapi.model.resources.BeerResource;

import java.time.LocalDateTime;
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
  @Column(name="brewery_id")
  private int breweryId;
  private double ibu;
  private double srm;
  private double upc;
  private String filepath = "";
  private String descript = "";
  @Column(name = "last_mod")
  private LocalDateTime lastMod = LocalDateTime.now();



  public Beer(double alcoholPercentage, String name)
  {
    this.name = name;
    this.alcoholPercentage = alcoholPercentage;
  }

  public Beer(BeerResource beer)
  {
    this.name = beer.getName();
    this.alcoholPercentage = beer.getAlcoholPercentage();
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

  public int getBreweryId()
  {
    return breweryId;
  }

  public void setBreweryId(int breweryId)
  {
    this.breweryId = breweryId;
  }

  public double getIbu()
  {
    return ibu;
  }

  public void setIbu(double ibu)
  {
    this.ibu = ibu;
  }

  public double getSrm()
  {
    return srm;
  }

  public void setSrm(double srm)
  {
    this.srm = srm;
  }

  public double getUpc()
  {
    return upc;
  }

  public void setUpc(double upc)
  {
    this.upc = upc;
  }

  public String getFilepath()
  {
    return filepath;
  }

  public void setFilepath(String filepath)
  {
    this.filepath = filepath;
  }

  public String getDescript()
  {
    return descript;
  }

  public void setDescript(String descript)
  {
    this.descript = descript;
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
