package com.bcnc;

import com.bcnc.application.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceService priceService;

    @Test
    public void getValidPriceTest() throws Exception {

        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        MvcResult mvcResult = mockMvc.perform(get("/bcnc/prices")
                .param("requestedDate", requestedDate.toString())
                .param("productId", productId.toString())
                .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void getMissingPriceTest() throws Exception {

        LocalDateTime requestedDate = LocalDateTime.of(2024, 6, 14, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        MvcResult mvcResult = mockMvc.perform(get("/bcnc/prices")
                        .param("requestedDate", requestedDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    public void enunciado1Test() throws Exception {

        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        MvcResult mvcResult = mockMvc.perform(get("/bcnc/prices")
                        .param("requestedDate", requestedDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(35.5))
                .andReturn();

    }

    @Test
    public void enunciado2Test() throws Exception {

        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        MvcResult mvcResult = mockMvc.perform(get("/bcnc/prices")
                        .param("requestedDate", requestedDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(25.45))
                .andReturn();

    }

    @Test
    public void enunciado3Test() throws Exception {

        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        MvcResult mvcResult = mockMvc.perform(get("/bcnc/prices")
                        .param("requestedDate", requestedDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(35.5))
                .andReturn();

    }

    @Test
    public void enunciado4Test() throws Exception {

        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        MvcResult mvcResult = mockMvc.perform(get("/bcnc/prices")
                        .param("requestedDate", requestedDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(30.5))
                .andReturn();

    }

    @Test
    public void enunciado5Test() throws Exception {

        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        MvcResult mvcResult = mockMvc.perform(get("/bcnc/prices")
                        .param("requestedDate", requestedDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(38.95))
                .andReturn();

    }

}
