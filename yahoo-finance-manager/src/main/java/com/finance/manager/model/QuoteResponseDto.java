package com.finance.manager.model;

import java.util.List;

public class QuoteResponseDto {
  private List<QuoteDto> result;
  private String error;  // Null if no error, otherwise an error message

  // Getters and setters
  public List<QuoteDto> getResult() {
      return result;
  }

  public void setResult(List<QuoteDto> result) {
      this.result = result;
  }

  public String getError() {
      return error;
  }

  public void setError(String error) {
      this.error = error;
  }

  @Override
  public String toString() {
      return "QuoteResponseDto{result=" + result + ", error='" + error + "'}";
  }
}
