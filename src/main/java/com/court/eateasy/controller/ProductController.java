package com.court.eateasy.controller;

import com.court.eateasy.constants.Constants;
import com.court.eateasy.entity.Products;
import com.court.eateasy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/santacruz/s1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductsById(@PathVariable Long id) {
        Optional<Products> product = productService.getProductsById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProducts(@RequestBody Products products) {
        productService.addProducts(products);
        return ResponseEntity.ok(Constants.PRODUCT_ADD_SUCCESS);
    }

    @PutMapping("/update")
    public Products updateProducts(@RequestBody Products products) {
        return productService.updateProducts(products);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProducts(@PathVariable Long id) {
        Optional<Products> product = productService.deleteProducts(id);
        return product.map(p -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
