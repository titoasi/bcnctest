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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@AllArgsConstructor
@RestController
@RequestMapping("/bcnc")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/prices")
    public ResponseEntity<Price> getSuitablePrice(
                    @RequestParam("requestedDate")String requestedDateString,
                    @RequestParam("productId") Long productId,
                    @RequestParam("brandId") Long brandId){

        //Validamos el formato de la fecha
        if (requestedDateString == null || productId == null || brandId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Convertimos la fecha a LocalDateTime
        LocalDateTime requestedDate;
        try {
            requestedDate = LocalDateTime.parse(requestedDateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return priceService.getSuitablePrice(requestedDate, productId, brandId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
