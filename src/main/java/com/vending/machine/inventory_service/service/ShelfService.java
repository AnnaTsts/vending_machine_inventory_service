package com.vending.machine.inventory_service.service;

import com.vending.machine.inventory_service.dto.response.ProductInfo;
import com.vending.machine.inventory_service.exception.InventoryIdException;
import com.vending.machine.inventory_service.model.Shelf;

public interface ShelfService {

    Shelf getShelfByDisplayName(String name);

    ProductInfo getProductInfoById(Long id);

    Shelf removeFromShelf(String name, long quantity) throws InventoryIdException;

    Shelf removeAllFromShelf(String name);

    Shelf addToShelf(String name, long quantity) throws InventoryIdException;
}
