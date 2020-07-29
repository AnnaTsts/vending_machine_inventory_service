package com.vending.machine.inventory_service.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "brand")
public class ProductBrand {
    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "brand_name")
    private String name;

    public ProductBrand(String name) {
        this.name = name;
    }
}
