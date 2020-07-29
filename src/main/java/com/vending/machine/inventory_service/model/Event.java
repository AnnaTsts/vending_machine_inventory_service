package com.vending.machine.inventory_service.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "event_type_id")
    private EventType eventType;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "event_time")
    private Timestamp eventTime;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;
}
