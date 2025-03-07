package com.finance.manager.model;

public class MetaDto {
  private String eventType;
  private Long dateEpochMs;
  private String amount;

  // Getters and setters
  public String getEventType() { return eventType; }
  public void setEventType(String eventType) { this.eventType = eventType; }
  public Long getDateEpochMs() { return dateEpochMs; }
  public void setDateEpochMs(Long dateEpochMs) { this.dateEpochMs = dateEpochMs; }
  public String getAmount() { return amount; }
  public void setAmount(String amount) { this.amount = amount; }

  @Override
  public String toString() {
      return "MetaDto{eventType='" + eventType + "', dateEpochMs=" + dateEpochMs + ", amount='" + amount + "'}";
  }
}
