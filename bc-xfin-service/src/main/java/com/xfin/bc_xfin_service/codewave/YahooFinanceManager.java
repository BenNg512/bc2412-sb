package com.xfin.bc_xfin_service.codewave;

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
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
public class YahooFinanceManager{

private final CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
private final ObjectMapper objectMapper = new ObjectMapper();

public QuoteDto getQuote(String symbol) {
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
            + symbol + "&crumb=" + crumbResponse.body();

        HttpRequest quoteRequest = HttpRequest.newBuilder()
        .uri(URI.create(apiUrl))
        .header("User-Agent", "Mozilla/5.0")
        .header("Accept", "application/json, text/plain, */*")
        .header("Accept-Language", "en-US,en;q=0.5")
        .header("Referer", "https://finance.yahoo.com/")
        .GET()
        .build();

        HttpResponse<String> quoteResponse = client.send(quoteRequest, HttpResponse.BodyHandlers.ofString());
        YahooFinanceManager.QuoteDto quoteDto = objectMapper.readValue(quoteResponse.body(), YahooFinanceManager.QuoteDto.class);

        return quoteDto;

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignores unknown fields
public static class QuoteDto {
    private QuoteResponse quoteResponse;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class QuoteResponse {
        private List<Result> result;
        private Object error;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private String language;
        private String region;
        private String quoteType;
        private String typeDisp;
        private String quoteSourceName;
        private boolean triggerable;
        private String customPriceAlertConfidence;
        private String currency;
        private long regularMarketTime;
        private double regularMarketChangePercent;
        private double regularMarketPrice;
        private String financialCurrency;
        private double regularMarketOpen;
        private long averageDailyVolume3Month;
        private long averageDailyVolume10Day;
        private double fiftyTwoWeekLowChange;
        private double fiftyTwoWeekLowChangePercent;
        private String fiftyTwoWeekRange;
        private double fiftyTwoWeekHighChange;
        private double fiftyTwoWeekHighChangePercent;
        private double fiftyTwoWeekLow;
        private double fiftyTwoWeekHigh;
        private double fiftyTwoWeekChangePercent;
        private long earningsTimestamp;
        private long earningsTimestampStart;
        private long earningsTimestampEnd;
        private long earningsCallTimestampStart;
        private long earningsCallTimestampEnd;
        private boolean isEarningsDateEstimate;
        private double trailingAnnualDividendRate;
        private double trailingPE;
        private double dividendRate;
        private double trailingAnnualDividendYield;
        private double dividendYield;
        private double epsTrailingTwelveMonths;
        private double epsForward;
        private double epsCurrentYear;
        private double priceEpsCurrentYear;
        private long sharesOutstanding;
        private double bookValue;
        private double fiftyDayAverage;
        private double fiftyDayAverageChange;
        private double fiftyDayAverageChangePercent;
        private double twoHundredDayAverage;
        private double twoHundredDayAverageChange;
        private double twoHundredDayAverageChangePercent;
        private long marketCap;
        private double forwardPE;
        private double priceToBook;
        private int sourceInterval;
        private int exchangeDataDelayedBy;
        private String averageAnalystRating;
        private List<Object> corporateActions;
        private String exchange;
        private String messageBoardId;
        private String exchangeTimezoneName;
        private String exchangeTimezoneShortName;
        private long gmtOffSetMilliseconds;
        private String market;
        private boolean esgPopulated;
        private boolean tradeable;
        private boolean cryptoTradeable;
        private boolean hasPrePostMarketData;
        private long firstTradeDateMilliseconds;
        private int priceHint;
        private double regularMarketChange;
        private double regularMarketDayHigh;
        private String regularMarketDayRange;
        private double regularMarketDayLow;
        private long regularMarketVolume;
        private double regularMarketPreviousClose;
        private double bid;
        private double ask;
        private int bidSize;
        private int askSize;
        private String fullExchangeName;
        private String marketState;
        private String shortName;
        private String longName;
        private String symbol;
    }
}
public static void main(String[] args) {
    YahooFinanceManager financeManager = new YahooFinanceManager();
    System.out.println(financeManager.getQuote("0005.HK"));
}
}
