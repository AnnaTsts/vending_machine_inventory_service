package com.vending.machine.inventory_service.controller;

import com.vending.machine.inventory_service.exception.InventoryIdException;
import com.vending.machine.inventory_service.model.ProductBrand;
import com.vending.machine.inventory_service.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/inventory/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Long id){
            return ResponseEntity.ok(brandService.getById(id));
    }

    @GetMapping()
    public ResponseEntity<?> getBrands(){
        return ResponseEntity.ok(brandService.getAll());
    }

    @PostMapping("/{name}")
    public ResponseEntity<?> createBrand(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.createBrand(name));
    }

    @PutMapping()
    public ResponseEntity<?> updateBrand(@RequestBody ProductBrand productBrand){
        try {
            brandService.updateBrand(productBrand);
            return ResponseEntity.ok().build();
        } catch (InventoryIdException e) {
            log.info(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

}
