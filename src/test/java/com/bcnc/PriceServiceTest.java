package com.bcnc;

import com.bcnc.application.service.PriceService;
import com.bcnc.domain.model.Brand;
import com.bcnc.domain.model.Price;
import com.bcnc.domain.port.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

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
        expectedPrice.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        expectedPrice.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));

        when(priceRepository.findSuitablePrice(requestedDate, productId, brandId)).thenReturn(Optional.of(expectedPrice));

        Optional<Price> actualPrice = priceService.getSuitablePrice(requestedDate, productId, brandId);

        assertEquals(Optional.of(expectedPrice), actualPrice);
        verify(priceRepository,times(1)).findSuitablePrice(requestedDate, productId, brandId);
    }

    @Test
    public void getSuitablePriceNotFoundTest() {
        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceRepository.findSuitablePrice(requestedDate, productId, brandId)).thenReturn(Optional.empty());

        Optional<Price> actualPrice = priceService.getSuitablePrice(requestedDate, productId, brandId);

        assertEquals(Optional.empty(), actualPrice);
        verify(priceRepository,times(1)).findSuitablePrice(requestedDate, productId, brandId);
    }

    @Test
    public void getSuitablePriceExceptionTest() {
        LocalDateTime requestedDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceRepository.findSuitablePrice(requestedDate, productId, brandId)).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> priceService.getSuitablePrice(requestedDate, productId, brandId));
    }

    @Test
    public void getSuitablePriceNullParametersTest() {
        assertThrows(IllegalArgumentException.class, () -> priceService.getSuitablePrice(null, null, null));

        verifyNoInteractions(priceRepository);
    }

}
