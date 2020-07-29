package com.vending.machine.inventory_service.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductInfo {
    private long productId;
    private BigDecimal productPrice;
    List<String> shelf;
    long quantity;

    public ProductInfo(long productId, BigDecimal productPrice) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.shelf = new ArrayList<>();
        this.quantity = 0;
    }
}
