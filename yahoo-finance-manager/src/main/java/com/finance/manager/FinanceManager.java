package com.finance.manager;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.manager.model.QuoteDto;

@Service
public class FinanceManager{

@Autowired
RestTemplate restTemplate;

private final CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
private final ObjectMapper objectMapper = new ObjectMapper();

public QuoteDto getQuote(String code) {
    String crumbUrl = "https://query1.finance.yahoo.com/v1/test/getcrumb";

    try {
        CookieStore cookieStore = cookieManager.getCookieStore();
        List<HttpCookie> existingCookies = cookieStore.get(URI.create("https://finance.yahoo.com"));
        HttpClient client = HttpClient.newBuilder()
                .cookieHandler(cookieManager)
                .followRedirects(Redirect.ALWAYS)
                .build();

        if (existingCookies.isEmpty()) {
            HttpRequest initialRequest = HttpRequest.newBuilder()
                    .uri(URI.create(crumbUrl))
                    .header("User-Agent", "Mozilla/5.0")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .GET()
                    .build();

            client.send(initialRequest, HttpResponse.BodyHandlers.ofString());
        }
        HttpRequest crumbRequest = HttpRequest.newBuilder()
                .uri(URI.create(crumbUrl))
                .header("User-Agent", "Mozilla/5.0")
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Referer", "https://finance.yahoo.com/")
                .GET()
                .build();

        HttpResponse<String> crumbResponse = client.send(crumbRequest, HttpResponse.BodyHandlers.ofString());
        //return crumbResponse.body();
        String apiUrl = "https://query1.finance.yahoo.com/v7/finance/quote?symbols="
            + code + ".HK&crumb=" + crumbResponse.body();

        HttpRequest quoteRequest = HttpRequest.newBuilder()
        .uri(URI.create(apiUrl))
        .header("User-Agent", "Mozilla/5.0")
        .header("Accept", "application/json, text/plain, */*")
        .header("Accept-Language", "en-US,en;q=0.5")
        .header("Referer", "https://finance.yahoo.com/")
        .GET()
        .build();

        HttpResponse<String> quoteResponse = client.send(quoteRequest, HttpResponse.BodyHandlers.ofString());
        QuoteDto quoteDto = objectMapper.readValue(quoteResponse.body(), QuoteDto.class);
        //QuoteDto quoteDto = this.restTemplate.getForObject(apiUrl, QuoteDto.class);
        return quoteDto;

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public static void main(String[] args) {
    FinanceManager financeManager = new FinanceManager();
    System.out.println(financeManager.getQuote("0005"));
}
}
