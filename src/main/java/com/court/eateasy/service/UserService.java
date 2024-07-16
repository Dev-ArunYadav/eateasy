package com.court.eateasy.service;

import com.court.eateasy.Repository.UserRepository;
import com.court.eateasy.constants.Constants;
import com.court.eateasy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    public void sendOtp(String  mobileNumber) {
        User existUser = userRepository.findByMobileNumber(mobileNumber);
        String otp = otpService.generateOtp(mobileNumber);
        if (existUser == null) {
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
            User existUser = userRepository.findByMobileNumber(mobileNumber);
            if (existUser == null) {
                // If OTP is verified for a new user, save them to the database
                User newUser = new User();
                newUser.setMobileNumber(mobileNumber);
                userRepository.save(newUser);
            }
            return true;
        }
        return false;
    }
}
