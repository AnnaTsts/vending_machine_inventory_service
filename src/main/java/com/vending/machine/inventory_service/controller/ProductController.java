package com.vending.machine.inventory_service.controller;

import com.vending.machine.inventory_service.dto.request.ProductCreationRequest;
import com.vending.machine.inventory_service.exception.InventoryIdException;
import com.vending.machine.inventory_service.model.Product;
import com.vending.machine.inventory_service.service.ShelfService;
import com.vending.machine.inventory_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/inventory/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShelfService inventoryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<?> getProductInfoById(@PathVariable Long id){
        return ResponseEntity.ok(inventoryService.getProductInfoById(id));
    }

    @GetMapping()
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductCreationRequest productCreationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productCreationRequest));
    }

    @PutMapping()
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        try {
            productService.updateProduct(product);
            return ResponseEntity.ok().build();
        } catch (InventoryIdException e) {
            log.info(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
}
