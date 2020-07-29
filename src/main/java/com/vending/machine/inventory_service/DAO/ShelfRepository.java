package com.vending.machine.inventory_service.DAO;

import com.vending.machine.inventory_service.model.Product;
import com.vending.machine.inventory_service.model.Shelf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends CrudRepository<Shelf, Long> {
    Shelf findShelfByDisplayName(String displayName);
    List<Shelf> findAllByProduct(Product product);
}
