package com.ClusteredData.clustereddatawarehouse.controller;

import com.ClusteredData.clustereddatawarehouse.dto.DealDTO;
import com.ClusteredData.clustereddatawarehouse.services.DealsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/deals")
public class DealsController {
    private static final Logger log = LoggerFactory.getLogger(DealsController.class);
    @Autowired
    DealsService dealsService;

    @PostMapping("/importDeal")
    public ResponseEntity<?> importDeal(@RequestBody DealDTO deal){
        return dealsService.importDeal(deal);
    }
}
