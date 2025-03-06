package com.bootcamp.web.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CoinDto {
    private String id;
    private String symbol;
    private String name;
    private String image;
    private Double currentPrice;
    private Long marketCap;
    private Integer marketCapRank;
    private Long fullyDilutedValuation;
    private Long totalVolume;
    @JsonProperty("high_24h")
    private Double high24h;
    @JsonProperty("low_24h")
    private Double low24h;
    @JsonProperty("price_change_24h")
    private Double priceChange24h;
    @JsonProperty("price_change_percentage_24h")
    private Double priceChangePercentage24h;
    @JsonProperty("market_cap_change_24h")
    private Double marketCapChange24h;
    @JsonProperty("market_cap_change_percentage_24h")
    private Double marketCapChangePercentage24h;
    private Double circulatingSupply;
    private Double totalSupply;
    private Double maxSupply;
    private Double ath;
    private Double athChangePercentage;
    private LocalDateTime athDate;
    private Double atl;
    private Double atlChangePercentage;
    private LocalDateTime atlDate;
    private Object roi;
    private ZonedDateTime lastUpdated;
    private String lastUpdatedHKTime;

    public void convertLastUpdatedToHKT() {
        if (lastUpdated != null) {
            this.lastUpdatedHKTime = lastUpdated.withZoneSameInstant(ZoneId.of("Asia/Hong_Kong"))
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'HKT'"));
        }
    }

}
