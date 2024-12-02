package com.bcnc.infrastructure.persistence;

import com.bcnc.domain.model.Price;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataPriceRepository extends JpaRepository<Price,Long> {

    @Query("SELECT * FROM PRICE p" +
            "WHERE p.BRAND_ID = :brandId " +
            "AND :requestedDate BETWEEN p.START_DATE AND p.END_DATE " +
            "AND p.PRODUCTD_ID = :productId " +
            "ORDER BY p.PRIOTRITY DESC")
    List<Price> findSuitablePrice(@Param("requestedDate")LocalDateTime requestedDate,
                                  @Param("productId")Long productId,
                                  @Param("brandId")Long brandId,
                                  Pageable pageable);

}
