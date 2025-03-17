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

public String getHKMarketState() {
    // "CLOSED" or "REGULAR"
    return this.getQuote("0005.HK").getQuoteResponse().getResult().get(0).getMarketState();
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
        private Long regularMarketTime;
        private Double regularMarketChangePercent;
        private Double regularMarketPrice;
        private String financialCurrency;
        private Double regularMarketOpen;
        private Long averageDailyVolume3Month;
        private Long averageDailyVolume10Day;
        private Double fiftyTwoWeekLowChange;
        private Double fiftyTwoWeekLowChangePercent;
        private String fiftyTwoWeekRange;
        private Double fiftyTwoWeekHighChange;
        private Double fiftyTwoWeekHighChangePercent;
        private Double fiftyTwoWeekLow;
        private Double fiftyTwoWeekHigh;
        private Double fiftyTwoWeekChangePercent;
        private Long earningsTimestamp;
        private Long earningsTimestampStart;
        private Long earningsTimestampEnd;
        private Long earningsCallTimestampStart;
        private Long earningsCallTimestampEnd;
        private boolean isEarningsDateEstimate;
        private Double trailingAnnualDividendRate;
        private Double trailingPE;
        private Double dividendRate;
        private Double trailingAnnualDividendYield;
        private Double dividendYield;
        private Double epsTrailingTwelveMonths;
        private Double epsForward;
        private Double epsCurrentYear;
        private Double priceEpsCurrentYear;
        private Long sharesOutstanding;
        private Double bookValue;
        private Double fiftyDayAverage;
        private Double fiftyDayAverageChange;
        private Double fiftyDayAverageChangePercent;
        private Double twoHundredDayAverage;
        private Double twoHundredDayAverageChange;
        private Double twoHundredDayAverageChangePercent;
        private Long marketCap;
        private Double forwardPE;
        private Double priceToBook;
        private Integer sourceInterval;
        private Integer exchangeDataDelayedBy;
        private String averageAnalystRating;
        private List<Object> corporateActions;
        private String exchange;
        private String messageBoardId;
        private String exchangeTimezoneName;
        private String exchangeTimezoneShortName;
        private Long gmtOffSetMilliseconds;
        private String market;
        private boolean esgPopulated;
        private boolean tradeable;
        private boolean cryptoTradeable;
        private boolean hasPrePostMarketData;
        private Long firstTradeDateMilliseconds;
        private Integer priceHint;
        private Double regularMarketChange;
        private Double regularMarketDayHigh;
        private String regularMarketDayRange;
        private Double regularMarketDayLow;
        private Long regularMarketVolume;
        private Double regularMarketPreviousClose;
        private Double bid;
        private Double ask;
        private Integer bidSize;
        private Integer askSize;
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
    System.out.println(financeManager.getHKMarketState());
}
}
