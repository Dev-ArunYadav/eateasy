package com.court.eateasy.controller;

import com.court.eateasy.constants.Constants;
import com.court.eateasy.entity.Users;
import com.court.eateasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/santacruz/u1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@Validated @RequestBody Users users) {
        userService.sendOtp(users.getMobileNumber());
        return ResponseEntity.ok(Constants.OTP_SEND_SUCCESS);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String,Boolean>> verifyOtp(@Validated @RequestBody Users users, @RequestParam String otp) {
        //String otp = users.getOtp();
        boolean isVerified = userService.verifyOtp(users.getMobileNumber(), otp);
        Map<String, Boolean> response = new HashMap<>();
        if (isVerified) {
            response.put(Constants.OTP_VERIFICATION_SUCCESS, true);
            return ResponseEntity.ok(response);
        } else {
            response.put(Constants.OTP_VERIFICATION_FAILED, false);
            return ResponseEntity.ok(response);
        }
    }
    
}
