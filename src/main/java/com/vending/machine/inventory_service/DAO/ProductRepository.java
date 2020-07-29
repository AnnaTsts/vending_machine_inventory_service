package com.vending.machine.inventory_service.DAO;

import com.vending.machine.inventory_service.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
}
