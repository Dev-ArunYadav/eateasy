package com.court.eateasy.service;

import com.court.eateasy.Repository.SellerRepository;
import com.court.eateasy.entity.Sellers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public void addSeller(Sellers sellers){
        sellerRepository.save(sellers);
    }

    public List<Sellers> getAllSellers(){
        return sellerRepository.findAll();
    }

    public Optional<Sellers> getSellerById(Long sellerId){
        return sellerRepository.findById(sellerId);
    }

    public void deleteSeller(Long sellerId) {
        sellerRepository.deleteById(sellerId);
    }

    public void updateSeller(Sellers sellers){
        sellerRepository.save(sellers);
    }

}
