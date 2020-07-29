package com.vending.machine.inventory_service.DAO;

import com.vending.machine.inventory_service.model.ProductBrand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<ProductBrand,Long> {
    public ProductBrand findFirstByName(String name);
}
