package com.vending.machine.inventory_service.service;

import com.vending.machine.inventory_service.exception.InventoryIdException;
import com.vending.machine.inventory_service.model.ProductBrand;

public interface BrandService {
    ProductBrand getById(Long id);

    Iterable<ProductBrand> getAll();

    ProductBrand createBrand(String name);

    void updateBrand(ProductBrand productBrand) throws InventoryIdException;
}
