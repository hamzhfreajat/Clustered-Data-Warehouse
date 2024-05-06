package com.ClusteredData.clustereddatawarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "currency_deal", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dealUniqueId"})
})
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CurrencyDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dealUniqueId;

    @Column(nullable = false, length = 3)
    private String fromCurrencyISOCode;

    @Column(nullable = false, length = 3)
    private String toCurrencyISOCode;

    @Column(nullable = false)
    private LocalDateTime dealTimestamp;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal dealAmountInOrderingCurrency;

    public CurrencyDeal() {
    }

    public CurrencyDeal(String dealUniqueId, String fromCurrencyISOCode, String toCurrencyISOCode, LocalDateTime dealTimestamp, BigDecimal dealAmountInOrderingCurrency) {
        this.dealUniqueId = dealUniqueId;
        this.fromCurrencyISOCode = fromCurrencyISOCode;
        this.toCurrencyISOCode = toCurrencyISOCode;
        this.dealTimestamp = dealTimestamp;
        this.dealAmountInOrderingCurrency = dealAmountInOrderingCurrency;
    }

    public Long getId() {
        return id;
    }

    public String getDealUniqueId() {
        return dealUniqueId;
    }

    public String getFromCurrencyISOCode() {
        return fromCurrencyISOCode;
    }

    public String getToCurrencyISOCode() {
        return toCurrencyISOCode;
    }

    public LocalDateTime getDealTimestamp() {
        return dealTimestamp;
    }

    public BigDecimal getDealAmountInOrderingCurrency() {
        return dealAmountInOrderingCurrency;
    }
}
