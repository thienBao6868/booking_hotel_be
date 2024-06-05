package com.Thienbao.booking.controller;

import com.Thienbao.booking.payload.request.LoginRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.security.CustomAuthenProvider;
import com.Thienbao.booking.service.AuthService;
import com.Thienbao.booking.utils.JwtHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
//        code được thực thi khi authenticate xong
//        Tận dụng api để tạo ra private-key
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
//        String key = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println("kiem tra   " + key);
        String authenToken = jwtHelper.generateToken(roleName);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Login success");
        baseResponse.setAccessToken(authenToken);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
