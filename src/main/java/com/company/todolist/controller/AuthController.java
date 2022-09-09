package com.company.todolist.controller;

import com.company.todolist.dto.TokenResponse;
import com.company.todolist.security.JwtProvider;
import com.company.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/signin")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    JwtProvider jwtProvider;

    @GetMapping
    public TokenResponse signIn(Principal principal) {
        UserDetails user = userService.loadUserByUsername(principal.getName());

        if ((user != null)){
            return new TokenResponse(jwtProvider.generateToken(principal.getName()));
        }
        return null;
    }

}
