package com.cpausermanager.cpa_user_manager.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.cpausermanager.cpa_user_manager.Models.User;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AuthController {
    @PostMapping("user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        String token = getJWTToken(username);
        User user = new User();
        user.setEmail(username);
        return user;
    }

    private String getJWTToken(String username) {
        
		return "Bearer " + "hello";
    }
    
}
