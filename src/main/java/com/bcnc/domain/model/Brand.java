package com.bcnc.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="BRAND")
public class Brand {

    @Id
    @Column(name = "BRAND_ID")
    private Long brandId;

    @Column(name="BRAND_NAME", nullable = false)
    private String brandName;
}
