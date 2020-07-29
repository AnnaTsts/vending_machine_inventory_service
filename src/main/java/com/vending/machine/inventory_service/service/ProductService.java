package com.vending.machine.inventory_service.service;

import com.vending.machine.inventory_service.dto.request.ProductCreationRequest;
import com.vending.machine.inventory_service.exception.InventoryIdException;
import com.vending.machine.inventory_service.model.Product;

public interface ProductService {
    Product getById(Long id);

    Object getAll();

    Product createProduct(ProductCreationRequest productCreationRequest);

    void updateProduct(Product product) throws InventoryIdException;
}
