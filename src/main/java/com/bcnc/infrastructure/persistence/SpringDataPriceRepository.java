package com.bcnc.infrastructure.persistence;

import com.bcnc.domain.model.Price;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataPriceRepository extends JpaRepository<Price,Long> {

    @Query("SELECT p FROM Price p " +
            "WHERE p.brandId.brandId = :brandId " +
            "AND :requestedDate BETWEEN p.startDate AND p.endDate " +
            "AND p.productId = :productId " +
            "ORDER BY p.priority DESC")
    List<Price> findSuitablePrice(@Param("requestedDate")LocalDateTime requestedDate,
                                  @Param("productId")Long productId,
                                  @Param("brandId")Long brandId,
                                  Pageable pageable);

}
