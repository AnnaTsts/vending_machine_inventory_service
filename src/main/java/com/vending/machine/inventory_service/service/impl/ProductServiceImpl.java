package com.vending.machine.inventory_service.service.impl;

import com.vending.machine.inventory_service.DAO.BrandRepository;
import com.vending.machine.inventory_service.DAO.ProductRepository;
import com.vending.machine.inventory_service.dto.request.ProductCreationRequest;
import com.vending.machine.inventory_service.exception.InventoryIdException;
import com.vending.machine.inventory_service.model.Product;
import com.vending.machine.inventory_service.model.ProductBrand;
import com.vending.machine.inventory_service.model.ProductType;
import com.vending.machine.inventory_service.service.BrandService;
import com.vending.machine.inventory_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandService brandService;

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductCreationRequest productRequest) {
        ProductBrand brand = brandRepository.findFirstByName(productRequest.getBrand());

        if (brand == null)
            brand = brandService.createBrand(productRequest.getBrand());

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setWeight(productRequest.getWeight());
        product.setProductBrand(brand);
        product.setProductTypeId(productRequest.getProductType());

        return productRepository.save(product);
    }

    public void updateProduct(Product product) throws InventoryIdException {
        if (productRepository.findById(product.getId()).isPresent()) {
            productRepository.save(product);
        } else throw new InventoryIdException("product", product.getId());
    }
}
