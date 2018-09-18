package com.beerapi.BeerAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class BeerController
{
  @GetMapping("/version")
  public Map<String, String> getVersion() {
    return Collections.singletonMap("version", "0.0.1-SNAPSHOT");
  }
}
