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

    public Optional<Price> getPriceApplicable(LocalDateTime requestedDate, Long productId, Long brandId){
        return priceRepository.findSuitablePrice(requestedDate,productId,brandId);
    }

}
