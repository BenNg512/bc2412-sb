package com.bootcamp.sbex2.bc_forum.lib;

import org.springframework.web.util.UriComponentsBuilder;

public class UrlBuilder {
  public static String buildHttps(String domain, ApiEndpoint endpoint) {
    return UriComponentsBuilder.newInstance()
      .scheme("https")
      .host(domain)
      .path(endpoint.toString())
      .build()
      .toUriString();
  }
  
}
