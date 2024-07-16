package com.court.eateasy.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private final Map<String, String> otpStorage = new HashMap<>();
    private final Map<String, Long> otpExpiryMap = new HashMap<>();
    private final int otpExpriryTime = 30;

    public String generateOtp(String mobilenumber) {
        Random random = new Random();
        String otp = String.format("%04d", random.nextInt(10000));
        otpStorage.put(mobilenumber, otp);
        otpExpiryMap.put(mobilenumber, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(otpExpriryTime));

        return otp;
    }

    public boolean validateOtp(String mobilenumber, String otp) {
        if(otpStorage.containsKey(mobilenumber)) {
            long expiryTime = otpExpiryMap.get(mobilenumber);
            if(System.currentTimeMillis() <= expiryTime && otp.equals(otpStorage.get(mobilenumber))) {
                otpStorage.remove(mobilenumber);
                otpExpiryMap.remove(mobilenumber);
                return true;
            }
        }
        return false;
    }
}

