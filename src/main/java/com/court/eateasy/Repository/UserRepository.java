package com.court.eateasy.Repository;

import com.court.eateasy.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByMobileNumber(String mobileNumber);
}
