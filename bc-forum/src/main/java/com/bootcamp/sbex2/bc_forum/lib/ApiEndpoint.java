package com.bootcamp.sbex2.bc_forum.lib;

import org.springframework.web.util.UriComponentsBuilder;

public enum ApiEndpoint {
  USERS("/users"),
  POSTS("/posts"),
  COMMENTS("/comments"),
  ALBUM("/albums"),
  PHOTOS("/photos"),
  TODOS("/todos"),
  ;

  private String endpoint;
  
  ApiEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  // public String getUrl(String domain) {
  //   return UriComponentsBuilder.newInstance()
  //     .scheme("https")
  //     .host(domain)
  //     .path(this.endpoint)
  //     .build()
  //     .toUriString();
  // }
  
  public String httpsBuilder(String domain) {
    return UriComponentsBuilder.newInstance()
      .scheme("https")
      .host(domain)
      .path(this.endpoint)
      .build()
      .toUriString();
  }

}
