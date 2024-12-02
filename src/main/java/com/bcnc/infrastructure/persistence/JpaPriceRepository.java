package com.bcnc.infrastructure.persistence;

import com.bcnc.domain.model.Price;
import com.bcnc.domain.port.PriceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public class JpaPriceRepository implements PriceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Price> findSuitablePrice(LocalDateTime requestedDate, Long productId, Long brandId) {

        String query ="""
            SELECT p
            FROM Price p
            WHERE p.brandId.brandId = :brandId
            AND p.productId = :productId
            AND :requestedDate BETWEEN p.startDate AND p.endDate
            ORDER BY p.priority DESC
            """;


        var result= entityManager.createQuery(query, Price.class)
                .setParameter("brandId", brandId)
                .setParameter("productId", productId)
                .setParameter("requestedDate", requestedDate)
                .setMaxResults(1)
                .getResultList();

        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));

    }
}
