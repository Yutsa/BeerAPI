package com.beerapi.BeerAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Beer
{
  @Id
  @GeneratedValue
  private long id;
}
