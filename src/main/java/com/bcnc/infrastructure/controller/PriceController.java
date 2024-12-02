package com.bcnc.infrastructure.controller;

import com.bcnc.application.service.PriceService;
import com.bcnc.domain.model.Price;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/bcnc/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public ResponseEntity<Price> getSuitablePrice(
                    @RequestParam("requestedDate")String requestedDateString,
                    @RequestParam("productId") Long productId,
                    @RequestParam("brandId") Long brandId){

        //Convertimos a fecha el String de entrada
        //TODO validar formato antes
        LocalDateTime requestedDate = LocalDateTime.parse(requestedDateString);

        Optional<Price> price = priceService.getPriceApplicable(requestedDate,productId,brandId);

        if (price.isPresent()) {
            return new ResponseEntity<>(price.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
