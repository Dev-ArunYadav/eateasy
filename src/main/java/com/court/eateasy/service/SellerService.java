package com.court.eateasy.service;

import com.court.eateasy.Repository.SellerRepository;
import com.court.eateasy.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public void addSeller(Seller seller){
        sellerRepository.save(seller);
    }

    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }
}
