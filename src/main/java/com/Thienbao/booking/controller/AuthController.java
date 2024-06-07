package com.Thienbao.booking.controller;

import com.Thienbao.booking.payload.request.LoginRequest;
import com.Thienbao.booking.payload.request.LogoutRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.security.DataSecurity;
import com.Thienbao.booking.service.AuthService;
import com.Thienbao.booking.utils.JwtHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

@RestController
@RequestMapping("${api.base-path}/auth")
public class AuthController {

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid LoginRequest loginRequest){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
        authenticationManager.authenticate(token);


        String roleName = authService.getUserByEmail(loginRequest.getEmail()).getRole().getName();

        // Logic (add thêm email của client vào token)
        DataSecurity dataSecurity = new DataSecurity();
        dataSecurity.setEmail(loginRequest.getEmail());
        dataSecurity.setRoleName(roleName);

        String authenToken = jwtHelper.generateToken(dataSecurity);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Login success");
        baseResponse.setAccessToken(authenToken);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid LogoutRequest logoutRequest){
        return new ResponseEntity<>("",HttpStatus.OK);
    }

}
