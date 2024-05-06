package com.ClusteredData.clustereddatawarehouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DealDTO {
    @JsonProperty("dealUniqueId")
    private String dealUniqueId;
    @JsonProperty("fromCurrencyISOCode")
    private String fromCurrencyISOCode;
    @JsonProperty("toCurrencyISOCode")
    private String toCurrencyISOCode;
    @JsonProperty("dealTimestamp")
    private LocalDateTime dealTimestamp;
    @JsonProperty("dealAmountInOrderingCurrency")
    private BigDecimal dealAmountInOrderingCurrency;
}
