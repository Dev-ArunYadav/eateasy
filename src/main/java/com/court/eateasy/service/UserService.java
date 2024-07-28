package com.court.eateasy.service;

import com.court.eateasy.Repository.UserRepository;
import com.court.eateasy.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    public void sendOtp(String  mobileNumber) {
        Users existUsers = userRepository.findByMobileNumber(mobileNumber);
        String otp = otpService.generateOtp(mobileNumber);
        if (existUsers == null) {
            // For new users, just generate and send OTP, but don't save user yet
            System.out.println("OTP for new user: " + otp);
        } else {
            // For existing users, send OTP and don't update the user
            System.out.println("OTP for existing user: " + otp);
        }
    }

    public Boolean verifyOtp(String mobileNumber, String otp) {
        boolean isVerified = otpService.validateOtp(mobileNumber, otp);
        if (isVerified) {
            Users existUsers = userRepository.findByMobileNumber(mobileNumber);
            if (existUsers == null) {
                // If OTP is verified for a new user, save them to the database
                Users newUsers = new Users();
                newUsers.setMobileNumber(mobileNumber);
                userRepository.save(newUsers);
            }
            return true;
        }
        return false;
    }
}
