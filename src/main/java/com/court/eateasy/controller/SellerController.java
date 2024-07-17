package com.court.eateasy.controller;

import com.court.eateasy.constants.Constants;
import com.court.eateasy.entity.Seller;
import com.court.eateasy.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/santacruz/s1/auth")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/add-seller")
    public ResponseEntity<String> addSeller(@Validated @RequestBody Seller seller){
        sellerService.addSeller(seller);
        return ResponseEntity.ok(Constants.SELLER_ADD_SUCCESS);
    }

    @GetMapping("/get-all-sellers")
    public ResponseEntity<String> getAllSellers(){
        return ResponseEntity.ok(sellerService.getAllSellers().toString());
    }

}
