package com.vending.machine.inventory_service.service.impl;

import com.vending.machine.inventory_service.DAO.BrandRepository;
import com.vending.machine.inventory_service.exception.InventoryIdException;
import com.vending.machine.inventory_service.model.ProductBrand;
import com.vending.machine.inventory_service.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public ProductBrand getById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Iterable<ProductBrand> getAll() {
        return brandRepository.findAll();
    }

    public ProductBrand createBrand(String name) {
        ProductBrand brand = new ProductBrand(name);
        ProductBrand savedBrand = brandRepository.save(brand);
        return savedBrand;
    }

    public void updateBrand(ProductBrand brand) throws InventoryIdException {
        if (brandRepository.findById(brand.getId()).isPresent()) {
            brandRepository.save(brand);
        } else throw new InventoryIdException("brand", brand.getId());
    }

}
