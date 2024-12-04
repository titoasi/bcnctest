package com.bcnc;

import com.bcnc.application.service.PriceService;
import com.bcnc.domain.model.Brand;
import com.bcnc.domain.model.Price;
import com.bcnc.infrastructure.controller.PriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getSuitablePriceOKTest() {
        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        Brand brand = new Brand();
        brand.setBrandId(brandId);
        brand.setBrandName("NEW_STORE");

        Price expectedPrice = new Price();
        expectedPrice.setProductId(productId);
        expectedPrice.setBrandId(brand);
        expectedPrice.setCurrency("EUR");
        expectedPrice.setAmount(BigDecimal.valueOf(55.55));
        expectedPrice.setStartDate(LocalDateTime.parse(LocalDateTime.of(2020, 6, 14, 0, 0, 0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        expectedPrice.setEndDate(LocalDateTime.parse(LocalDateTime.of(2020, 12, 31, 23, 59, 59).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));

        when(priceService.getSuitablePrice(requestedDate, productId, brandId)).thenReturn(Optional.of(expectedPrice));

        ResponseEntity<Price> response = priceController.getSuitablePrice(requestedDate.toString(), productId, brandId);

        assertEquals(200, response.getStatusCode().value());

    }

    @Test
    public void getSuitablePriceNotFoundTest() {
        LocalDateTime requestedDate = LocalDateTime.of(2024, 6, 14, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceService.getSuitablePrice(requestedDate, productId, brandId)).thenReturn(Optional.empty());

        ResponseEntity<Price> response = priceController.getSuitablePrice(requestedDate.toString(), productId, brandId);

        assertEquals(404, response.getStatusCode().value());

    }

    @Test
    public void getSuitablePriceInvalidDateFormatTest() {
        String invalidDate = "invalid-date";
        Long productId = 35455L;
        Long brandId = 1L;

        ResponseEntity<Price> response = priceController.getSuitablePrice(invalidDate, productId, brandId);

        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    public void getSuitablePriceNullParametersTest() {
        ResponseEntity<Price> response = priceController.getSuitablePrice(null, null, null);

        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    public void getSuitablePriceWrongProductIdTest() {
        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = -1L;
        Long brandId = 1L;

        ResponseEntity<Price> response = priceController.getSuitablePrice(requestedDate.toString(), productId, brandId);

        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    public void getSuitablePriceWrongBrandTest() {
        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = -1L;
        Long brandId = 99999L;

        ResponseEntity<Price> response = priceController.getSuitablePrice(requestedDate.toString(), productId, brandId);

        assertEquals(404, response.getStatusCode().value());
    }

}
