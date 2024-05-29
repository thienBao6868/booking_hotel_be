package com.Thienbao.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base-path}/auth")
public class AuthController {
    @GetMapping("/login")
    public String login(){
        return "hello";
    }
}
