package com.vending.machine.inventory_service.exception;

public class InventoryIdException extends Exception {

    public InventoryIdException(String message) {
        super("Wrong id " + message);
    }

    public InventoryIdException(String ot, long id) {
        super("Wrong id " + id + " for object " + ot);
    }

}
