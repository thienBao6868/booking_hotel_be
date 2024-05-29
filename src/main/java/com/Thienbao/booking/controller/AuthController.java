package com.Thienbao.booking.controller;

import com.Thienbao.booking.security.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base-path}/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,password);

        authenticationManager.authenticate(token);

        return new ResponseEntity<>("Login Success", HttpStatus.OK);
    }
}
