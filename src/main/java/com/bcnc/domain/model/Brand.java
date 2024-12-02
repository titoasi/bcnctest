package com.bcnc.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="BRAND")
public class Brand {

    @Id
    @Column(name = "BRAND_ID")
    private Long brandId;

    @JsonIgnore
    @Column(name="BRAND_NAME", nullable = false)
    private String brandName;
}
