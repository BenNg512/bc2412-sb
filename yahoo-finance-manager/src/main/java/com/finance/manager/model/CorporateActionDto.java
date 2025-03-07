package com.finance.manager.model;

public class CorporateActionDto {
  private String header;
  private String message;
  private MetaDto meta;

  // Getters and setters
  public String getHeader() { return header; }
  public void setHeader(String header) { this.header = header; }
  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }
  public MetaDto getMeta() { return meta; }
  public void setMeta(MetaDto meta) { this.meta = meta; }

  @Override
  public String toString() {
      return "CorporateActionDto{header='" + header + "', message='" + message + "', meta=" + meta + "}";
  }
}
