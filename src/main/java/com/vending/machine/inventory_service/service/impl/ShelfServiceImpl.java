package com.vending.machine.inventory_service.service.impl;

import com.vending.machine.inventory_service.DAO.ShelfRepository;
import com.vending.machine.inventory_service.config.InventoryConstants;
import com.vending.machine.inventory_service.dto.response.ProductInfo;
import com.vending.machine.inventory_service.exception.InventoryIdException;
import com.vending.machine.inventory_service.model.Product;
import com.vending.machine.inventory_service.model.Shelf;
import com.vending.machine.inventory_service.service.ShelfService;
import com.vending.machine.inventory_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfServiceImpl implements ShelfService {
    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Shelf getShelfByDisplayName(String name) {
        return shelfRepository.findShelfByDisplayName(name);
    }

    @Override
    public ProductInfo getProductInfoById(Long id) {
        Product product = productService.getById(id);

        if (product != null) {
            ProductInfo productInfo = new ProductInfo(product.getId(), product.getPrice());
            List<Shelf> allByProduct = shelfRepository.findAllByProduct(product);

            allByProduct.stream()
                    .filter(shelf -> shelf.getQuantity() > 0)
                    .forEach(shelf ->
                    {
                        productInfo.setQuantity(productInfo.getQuantity() + shelf.getQuantity());
                        productInfo.getShelf().add(shelf.getDisplayName());
                    });

            return productInfo;
        }
        return null;
    }

    @Override
    public Shelf removeAllFromShelf(String shelfName) {
        Shelf shelfByDisplayName = shelfRepository.findShelfByDisplayName(shelfName);
        shelfByDisplayName.setQuantity(0);
        shelfByDisplayName.setProduct(null);
        return shelfRepository.save(shelfByDisplayName);
    }

    @Override
    public Shelf removeFromShelf(String shelfName, long quantity) throws InventoryIdException {
        Shelf shelf = shelfRepository.findShelfByDisplayName(shelfName);

        if (shelf.getQuantity() > quantity) {
            shelf.setQuantity(shelf.getQuantity() - quantity);
            return shelfRepository.save(shelf);
        } else throw new InventoryIdException("Trying to remove quantity is bigger than presented in shelf");
    }

    @Override
    public Shelf addToShelf(String shelfName, long quantity) throws InventoryIdException {
        Shelf shelf = shelfRepository.findShelfByDisplayName(shelfName);

        if (shelf.getProduct() != null &&
                shelf.getQuantity() + quantity < InventoryConstants.MAX_PRODUCT_IN_ONE_SHELL) {
            shelf.setQuantity(shelf.getQuantity() + quantity);
            return shelfRepository.save(shelf);
        } else throw new InventoryIdException("Quantity is invalid");
    }

    public Shelf addNewProduct(String shelfName, long quantity, long productId) throws InventoryIdException {
        Shelf shelf = shelfRepository.findShelfByDisplayName(shelfName);
        if (shelf.getQuantity() == 0) {
            Product product = productService.getById(productId);
            if (product != null && quantity > 0 && quantity < InventoryConstants.MAX_PRODUCT_IN_ONE_SHELL) {
                shelf.setQuantity(quantity);
                shelf.setProduct(product);
                return shelfRepository.save(shelf);
            } else throw new InventoryIdException("Quantity is invalid");

        } else throw new InventoryIdException("There is other product in shelf");
    }

    public boolean isShelfEmpty(String shelfName) {
        Shelf shelfByDisplayName = shelfRepository.findShelfByDisplayName(shelfName);
        return shelfByDisplayName.getQuantity() == 0;
    }
}
