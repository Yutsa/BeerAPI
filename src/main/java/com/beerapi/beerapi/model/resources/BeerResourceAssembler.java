package com.beerapi.beerapi.model.resources;

import com.beerapi.beerapi.controller.BeerController;
import com.beerapi.beerapi.model.entities.Beer;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class BeerResourceAssembler extends ResourceAssemblerSupport<Beer, BeerResource>
{
  public BeerResourceAssembler()
  {
    super(BeerController.class, BeerResource.class);
  }

  @Override
  public BeerResource toResource(Beer entity)
  {
    return createResourceWithId(entity.getId(), entity);
  }

  @Override
  protected BeerResource instantiateResource(Beer entity)
  {
    return new BeerResource(entity.getAlcoholPercentage(), entity.getName());
  }
}
