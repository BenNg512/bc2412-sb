package com.example.restful.demo_restful.service;

import org.springframework.stereotype.Service;
import com.example.restful.demo_restful.model.Cat;
import com.example.restful.demo_restful.model.CatDatabase;
import com.example.restful.demo_restful.service.impl.CatServiceImpl;

@Service // Bean
public class CatService implements CatServiceImpl {
  // stateless -> no attribute
    public boolean put(Cat cat) {
    for (int i = 0; i < CatDatabase.HOME.length; i++) {
      if (CatDatabase.HOME[i] == null) {
        CatDatabase.HOME[i] = cat;
        return true;
      }
    }
    return false;
  }
}
