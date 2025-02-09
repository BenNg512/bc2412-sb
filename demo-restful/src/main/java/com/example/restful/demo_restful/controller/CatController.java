package com.example.restful.demo_restful.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.restful.demo_restful.model.Cat;
import com.example.restful.demo_restful.model.CatDatabase;
import com.example.restful.demo_restful.service.CatService;

// GetMapping
// PostMapping
// PutMapping
// DeleteMapping
// PatchMapping

// ! RESTful API -> GET/POST/DELETE/PUT/PATCH
// Control single resource by GET/POST/DELETE/PUT/PATCH

// Controller -> The ways to control Cat resource
// insert, update, delete, select
@Controller
@ResponseBody
public class CatController {
  // Controller -> Service -> CatDatabase

  // Dependency Injection (Spring Core Concept)
  // Autowired: Try to find an object which fits into CatService
  //! if fails , server start fails

  // Field Injection
  // @Autowired
  private CatService catService;

  // Constructor Injection
  @Autowired(required = false) // find an object which fits into CatService, else fail server start
  public CatController(CatService catService) {
    this.catService = catService;
  }

  // insert
  @PostMapping(value = "/cat")
  public Cat createCat(@RequestBody Cat cat) {
    if (CatDatabase.put(cat))
      return cat;
    return null;
  }

  // Arrays.asList() vs List.of() vs new ArrayList<>()

  // Get All Cats
  @GetMapping(value = "/cats")
  public List<Cat> getCats() {
    return List.of(CatDatabase.HOME);
  }

  // Get Cat By id
  // http://localhost:8082/cat?id=1
  // Deserialization
  @GetMapping(value = "/cat")
  public Cat getCat(@RequestParam Long id) {
    return CatDatabase.find(id).orElse(null);
  }

  // http://localhost:8082/cat?id=1
  @DeleteMapping(value = "/cat")
  public Boolean deleteCat(@RequestParam Long id) {
    return CatDatabase.delete(id);
  }

  // HashMap.put() -> if exists, override, otherwise, create new
  @PutMapping(value = "/cat")
  public Boolean updateCat(@RequestParam Long id, @RequestBody Cat cat) {
    return CatDatabase.update(id, cat);
  }

  @PatchMapping(value = "/cat/name/{name}")
  public Boolean patchCatName(@RequestParam Long id, @PathVariable String name) {
    return CatDatabase.patchName(id, name);
  }

}