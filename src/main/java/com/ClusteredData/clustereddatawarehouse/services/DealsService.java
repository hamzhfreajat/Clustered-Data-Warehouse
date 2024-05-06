package com.ClusteredData.clustereddatawarehouse.services;

import com.ClusteredData.clustereddatawarehouse.dto.DealDTO;
import com.ClusteredData.clustereddatawarehouse.exception.GenralException;
import com.ClusteredData.clustereddatawarehouse.model.CurrencyDeal;
import com.ClusteredData.clustereddatawarehouse.repo.CurrencyDealRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Field;



@Service
@Slf4j
public class DealsService {

    private static final Logger log = LoggerFactory.getLogger(DealsService.class);

    @Autowired
    private CurrencyDealRepository currencyDealRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = DataIntegrityViolationException.class)
    public ResponseEntity<?> importDeal(DealDTO deal) {
        try {
            log.info("Importing in progress ... ");

            ResponseEntity<GenralException> BAD_REQUEST = checkFields(deal , DealDTO.class.getDeclaredFields());
            if (checkFields(deal, DealDTO.class.getDeclaredFields()) != null) return BAD_REQUEST;

            currencyDealRepository.save(CurrencyDeal.builder()
                    .dealUniqueId(deal.getDealUniqueId())
                    .fromCurrencyISOCode(deal.getFromCurrencyISOCode())
                    .toCurrencyISOCode(deal.getToCurrencyISOCode())
                    .dealTimestamp(deal.getDealTimestamp())
                    .dealAmountInOrderingCurrency(deal.getDealAmountInOrderingCurrency())
                    .build());

            log.info("Deal imported successfully");

            return ResponseEntity.ok().body("Deal imported successfully");

        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage() , new GenralException("Error importing deal: " + e.getMessage()));
            return ResponseEntity.status(400).body(new GenralException("Error importing deal: " + e.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage() , new GenralException("Error importing deal: " + e.getMessage()));
            return ResponseEntity.status(500).body(new GenralException("Error importing deal: " + e.getMessage()));
        }
    }

    private ResponseEntity<GenralException> checkFields(DealDTO deal , Field[] fields) throws IllegalAccessException {
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(deal) == null) {
                log.error(field.getName() + " field is required" , new GenralException(field.getName() + " field is required"));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenralException(field.getName() + " field is required"));
            }
        }
        return null;
    }

}
