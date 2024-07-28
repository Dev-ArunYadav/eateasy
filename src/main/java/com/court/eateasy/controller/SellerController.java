package com.court.eateasy.controller;

import com.court.eateasy.constants.Constants;
import com.court.eateasy.entity.Sellers;
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

    @PostMapping("/add-sellers")
    public ResponseEntity<String> addSeller(@Validated @RequestBody Sellers sellers){
        sellerService.addSeller(sellers);
        return ResponseEntity.ok(Constants.SELLER_ADD_SUCCESS);
    }

    @GetMapping("/get-all-sellers")
    public ResponseEntity<String> getAllSellers(){
        return ResponseEntity.ok(sellerService.getAllSellers().toString());
    }

}
