package com.court.eateasy.controller;

import com.court.eateasy.constants.Constants;
import com.court.eateasy.entity.User;
import com.court.eateasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/santacruz/u1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@Validated @RequestBody User user) {
        userService.sendOtp(user.getMobileNumber());
        return ResponseEntity.ok(Constants.OTP_SEND_SUCCESS);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@Validated @RequestBody User user, @RequestParam String otp) {
        //String otp = user.getOtp();
        boolean isVerified = userService.verifyOtp(user.getMobileNumber(), otp);
        if (isVerified) {
            return ResponseEntity.ok(Constants.OTP_VERIFICATION_SUCCESS);
        } else {
            return ResponseEntity.ok(Constants.OTP_VERIFICATION_FAILED);
        }
    }

}
