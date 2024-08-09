package com.court.eateasy.controller;

import com.court.eateasy.constants.Constants;
import com.court.eateasy.entity.Sellers;
import com.court.eateasy.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/get-seller-by-id")
    public ResponseEntity<String> getSellerById(@RequestParam("seller_id") Long seller_id){
        Optional<Sellers> seller = sellerService.getSellerById(seller_id);
        if(seller.isPresent()){
            return ResponseEntity.ok(seller.toString());
        }
        else{
            return ResponseEntity.ok(Constants.SELLER_NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-seller")
    public ResponseEntity<String> deleteSeller(@RequestParam("seller_id") Long seller_id){
        sellerService.deleteSeller(seller_id);
        return ResponseEntity.ok(Constants.SELLER_DELETE_SUCCESS);
    }

    @PutMapping("/update-seller")
    public ResponseEntity<String> updateSeller(@RequestBody Sellers sellers){
        sellerService.updateSeller(sellers);
        return ResponseEntity.ok(Constants.SELLER_UPDATE_SUCCESS);
    }

}
