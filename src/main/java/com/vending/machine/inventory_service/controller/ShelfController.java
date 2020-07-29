package com.vending.machine.inventory_service.controller;

import com.vending.machine.inventory_service.exception.InventoryIdException;
import com.vending.machine.inventory_service.service.ShelfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
@RequestMapping("/api/inventory/shelf")
public class ShelfController {
    @Autowired
    ShelfService inventoryService;

    @GetMapping("/{name}")
    public ResponseEntity<?> getShelfByName(@PathVariable String name){
        return ResponseEntity.ok(inventoryService.getShelfByDisplayName(name));
    }

    @PostMapping("/{name}/remove/{quantity}")
    public ResponseEntity<?> removeProductFromShelf(@PathVariable String name, @PathVariable long quantity){
        try {
            return ResponseEntity.ok(inventoryService.removeFromShelf(name,quantity));
        } catch (InventoryIdException e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PostMapping("/{name}/removeAll")
    public ResponseEntity<?> removeAllProductFromShelf(@PathVariable String name){
        return ResponseEntity.ok(inventoryService.removeAllFromShelf(name));
    }

    @PostMapping("/{name}/add/{quantity}")
    public ResponseEntity<?> addProductToShelf(@PathVariable String name,@PathVariable long quantity){
        try {
            return ResponseEntity.ok(inventoryService.addToShelf(name,quantity));
        } catch (InventoryIdException e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
