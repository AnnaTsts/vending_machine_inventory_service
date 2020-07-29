package com.vending.machine.inventory_service.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreationRequest {
    private String name;
    private BigDecimal weight;
    private BigDecimal price;
    private String brand;
    private long productType;
}
