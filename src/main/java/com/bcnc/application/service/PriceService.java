package com.bcnc.application.service;

import com.bcnc.domain.model.Price;
import com.bcnc.domain.port.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Optional<Price> getSuitablePrice(LocalDateTime requestedDate, Long productId, Long brandId){

        if (requestedDate == null || productId == null || brandId == null) {
            throw new IllegalArgumentException("Parameters must not be null");
        }
        return priceRepository.findSuitablePrice(requestedDate,productId,brandId);
    }

}
