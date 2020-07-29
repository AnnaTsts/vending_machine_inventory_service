package com.vending.machine.inventory_service.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name")
    private String name;

    private BigDecimal weight;

    private BigDecimal price;

    @Column(name = "product_type_id")
    private long productTypeId;

    //private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private ProductBrand productBrand;
}
