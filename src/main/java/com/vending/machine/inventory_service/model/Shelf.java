package com.vending.machine.inventory_service.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "store")
public class Shelf {
    @Id
    @Column(name = "shelf_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "shelf_display_name")
    private String displayName;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private long quantity;
}
