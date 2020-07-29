package com.vending.machine.inventory_service.model;

import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
public enum ProductType {
    WATER(1,"water"),
    TEA(2,"tea"),
    COKE(3,"coke"),
    POP(4,"pop");

    private final long productTypeId;
    private final String productTypeName;

    ProductType(long productTypeId, String productTypeName) {
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
    }

    public static ProductType getProductTypeByName(String productTypeName){
        Optional<ProductType> type = Stream.of(ProductType.values())
                .filter(productType -> productType.productTypeName.equals(productTypeName))
                .findFirst();
        return type.orElse(null);
    }

    public static ProductType getProductTypeById(long productTypeId){
        Optional<ProductType> type = Stream.of(ProductType.values())
                .filter(productType -> productType.productTypeId == productTypeId)
                .findFirst();
        return type.orElse(null);
    }
}
