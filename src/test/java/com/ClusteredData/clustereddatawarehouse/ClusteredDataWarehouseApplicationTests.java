package com.ClusteredData.clustereddatawarehouse;

import com.ClusteredData.clustereddatawarehouse.controller.DealsController;
import com.ClusteredData.clustereddatawarehouse.dto.DealDTO;
import com.ClusteredData.clustereddatawarehouse.repo.CurrencyDealRepository;
import com.ClusteredData.clustereddatawarehouse.services.DealsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@SpringBootTest
@AutoConfigureMockMvc
class ClusteredDataWarehouseApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DealsService dealsService;

	@Autowired
	private CurrencyDealRepository currencyDealRepository;

	@Autowired
	private DealsController dealsController;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	public void saveDealTest() throws Exception {
		DealDTO dealDto = DealDTO.builder()
				.dealUniqueId("36")
				.fromCurrencyISOCode("JOD")
				.toCurrencyISOCode("SAR")
				.dealTimestamp(LocalDateTime.parse("2024-05-05T12:00:00"))
				.dealAmountInOrderingCurrency(BigDecimal.valueOf(100))
				.build();


		String jsonPayload = objectMapper.writeValueAsString(dealDto);

		// Perform the POST request and expect OK status
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/deals/importDeal")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPayload))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Deal imported successfully"));
	}

	@Test
	public void testWrongDataError() throws Exception {
		DealDTO dealDto = DealDTO.builder()
				.dealUniqueId("18")
				.fromCurrencyISOCode("JODsdadads")
				.toCurrencyISOCode("SAR")
				.dealTimestamp(LocalDateTime.parse("2024-05-05T12:00:00"))
				.dealAmountInOrderingCurrency(BigDecimal.valueOf(100))
				.build();


		String jsonPayload = objectMapper.writeValueAsString(dealDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/deals/importDeal")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPayload))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Transaction silently rolled back because it has been marked as rollback-only"));

	}

	@Test
	public void testMissingField() throws Exception {
		DealDTO dealDto = DealDTO.builder()
				.dealUniqueId("18")
				.fromCurrencyISOCode(null)
				.toCurrencyISOCode("SAR")
				.dealTimestamp(LocalDateTime.parse("2024-05-05T12:00:00"))
				.dealAmountInOrderingCurrency(BigDecimal.valueOf(100))
				.build();


		String jsonPayload = objectMapper.writeValueAsString(dealDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/deals/importDeal")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPayload))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("fromCurrencyISOCode field is required"));

	}
}