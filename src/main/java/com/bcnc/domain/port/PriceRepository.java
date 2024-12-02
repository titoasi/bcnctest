package com.bcnc.domain.port;

import com.bcnc.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findSuitablePrice(LocalDateTime requestedDate, Long productId, Long brandId);
}
