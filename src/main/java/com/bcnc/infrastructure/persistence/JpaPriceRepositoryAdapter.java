package com.bcnc.infrastructure.persistence;

import com.bcnc.domain.model.Price;
import com.bcnc.domain.port.PriceRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class JpaPriceRepositoryAdapter implements PriceRepository {
    @Override
    public Optional<Price> findSuitablePrice(LocalDateTime requestedDate, Long productId, Long brandId) {
        return Optional.empty();
    }
}
