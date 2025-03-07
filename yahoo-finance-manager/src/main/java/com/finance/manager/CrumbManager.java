package com.finance.manager;

import java.net.CookieManager;
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
    public String getCrumb() {
        try {
            CookieManager cookieManager = new CookieManager();
            CookieStore cookieStore = cookieManager.getCookieStore();
            List<HttpCookie> existingCookies = cookieStore.getCookies();
            System.out.println("Step 0: Existing cookies: " + existingCookies);

            if (!existingCookies.isEmpty() && hasBCookie(existingCookies)) {
                System.out.println("Step 0: Valid 'B' cookie found, reusing it.");
            } else {
                cookieStore.removeAll();
                System.out.println("Step 0: Cookies cleared, fetching new session cookie.");

                HttpClient client = HttpClient.newBuilder()
                    .cookieHandler(cookieManager)
                    .followRedirects(Redirect.ALWAYS)
                    .build();

                HttpRequest cookieRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://finance.yahoo.com"))
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .GET()
                    .build();
                System.out.println("Step 1: Sending cookie request to finance.yahoo.com...");
                HttpResponse<String> cookieResponse = client.send(cookieRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println("Cookie request status: " + cookieResponse.statusCode());
                System.out.println("Cookies received: " + cookieStore.getCookies());
                System.out.println("Cookie response headers: " + cookieResponse.headers().map());

                if (cookieResponse.statusCode() != 200) {
                    System.out.println("Cookie response body: " + cookieResponse.body());
                    throw new RuntimeException("Failed to fetch session cookie, HTTP code: " + cookieResponse.statusCode());
                }
            }

            HttpClient client = HttpClient.newBuilder()
                .cookieHandler(cookieManager)
                .followRedirects(Redirect.ALWAYS)
                .build();

            HttpRequest crumbRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://query1.finance.yahoo.com/v1/test/getcrumb"))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .header("Accept", "text/plain")
                .GET()
                .build();
            System.out.println("Step 2: Sending crumb request to getcrumb...");
            HttpResponse<String> crumbResponse = client.send(crumbRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Crumb request status: " + crumbResponse.statusCode());
            System.out.println("Crumb response headers: " + crumbResponse.headers().map());
            System.out.println("Crumb response body: " + crumbResponse.body());
            System.out.println("Cookies after crumb request: " + cookieStore.getCookies());

            if (crumbResponse.statusCode() == 200) {
                String crumb = crumbResponse.body();
                if (crumb == null || crumb.trim().isEmpty() || crumb.contains("error")) {
                    System.out.println("Invalid crumb received: " + crumb);
                    return null;
                }
                return crumb;
            } else {
                throw new RuntimeException("Failed to fetch crumb, HTTP code: " + crumbResponse.statusCode());
            }
        } catch (java.net.ConnectException e) {
            System.out.println("Connection refused: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private boolean hasBCookie(List<HttpCookie> cookies) {
        return cookies.stream().anyMatch(cookie -> "B".equals(cookie.getName()));
    }

    public static void main(String[] args) {
        CrumbManager crumbManager = new CrumbManager();
        String crumb = crumbManager.getCrumb();
        System.out.println("Fetched crumb: " + crumb);
    }
}
