package com.example.restful.demo_restful.model;

import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResult {
  private String message;
}
