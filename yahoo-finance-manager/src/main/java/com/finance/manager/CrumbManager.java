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
import org.springframework.stereotype.Service;

@Service
public class CrumbManager {
    
    public final CookieManager cookieManager;

    public CrumbManager() {
        cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
    }

    public String getCrumb() {
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
            return crumbResponse.body();
            // String apiUrl = "https://query1.finance.yahoo.com/v7/finance/quote?symbols="
            //     + "0005" + ".HK&crumb=" + crumbResponse.body();

            // HttpRequest quoteRequest = HttpRequest.newBuilder()
            // .uri(URI.create(apiUrl))
            // .header("User-Agent", "Mozilla/5.0")
            // .header("Accept", "application/json, text/plain, */*")
            // .header("Accept-Language", "en-US,en;q=0.5")
            // .header("Referer", "https://finance.yahoo.com/")
            // .GET()
            // .build();

            // HttpResponse<String> quoteResponse = client.send(quoteRequest, HttpResponse.BodyHandlers.ofString());
            // ObjectMapper objectMapper = new ObjectMapper();
            // JsonNode jsonNode = objectMapper.readTree(quoteResponse.body());
            // return jsonNode.toPrettyString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching crumb";
        }
    }

    public static void main(String[] args) {
        CrumbManager crumbManager = new CrumbManager();
        String crumb = crumbManager.getCrumb();
        System.out.println("Crumb: " + crumb);
    }
}
