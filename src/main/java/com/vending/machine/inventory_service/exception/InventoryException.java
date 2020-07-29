package com.vending.machine.inventory_service.exception;

public class InventoryException extends Throwable{

    public InventoryException(String message) {
        super("Inventory Exception:"+message);
    }
}
