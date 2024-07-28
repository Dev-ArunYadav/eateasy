package com.court.eateasy.service;

import com.court.eateasy.Repository.ProductRepository;
import com.court.eateasy.constants.Constants;
import com.court.eateasy.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProducts(Products products) {
        if(products == null)
            throw new IllegalArgumentException(Constants.PRODUCT_ADD_FAILED);
    }

    public Optional<Products> getProductsById(Long id) {
        return productRepository.findById(id);
    }

    public Products updateProducts(Products products) {
        return productRepository.save(products);
    }

    public Optional<Products> deleteProducts(Long id) {
        Optional<Products> product = productRepository.findById(id);

        product.ifPresent(p -> productRepository.delete(p));
        return product;
    }
}
