package com.inditex.core.platform;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PricesApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvFileSource(numLinesToSkip = 1, delimiterString = "|", resources = "/getProductPrices.csv")
    void priceResquestsTest(Integer brandId, Integer productId, String fee, String expected) throws Exception {
        String endpoint = String.format("/brands/%d/products/%d/price", brandId, productId);
        this.mockMvc.perform(get(endpoint).queryParam("fee", fee))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @ParameterizedTest
    @CsvFileSource(numLinesToSkip = 1, delimiterString = "|", resources = "/getProductPricesError.csv")
    void priceResquestsTestShouldReturn404(Integer brandId, Integer productId, String fee, int expectedStatusCode) throws Exception {
        String endpoint = String.format("/brands/%d/products/%d/price", brandId, productId);
        this.mockMvc.perform(get(endpoint).queryParam("fee", fee))
                .andExpect(status().is(expectedStatusCode));
    }

}