package com.court.eateasy.Repository;

import com.court.eateasy.entity.Sellers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Sellers, Long> {
}
