package com.ClusteredData.clustereddatawarehouse.repo;

import com.ClusteredData.clustereddatawarehouse.model.CurrencyDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyDealRepository extends JpaRepository<CurrencyDeal, Long> {
    Optional<CurrencyDeal> findByDealUniqueId(String dealUniqueId);
}
