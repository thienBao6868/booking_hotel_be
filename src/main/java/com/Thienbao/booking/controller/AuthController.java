package com.Thienbao.booking.controller;

import com.Thienbao.booking.payload.request.LoginRequest;
import com.Thienbao.booking.security.CustomAuthenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

@RestController
@RequestMapping("${api.base-path}/auth")
public class AuthController {

    @Value("${jwt.private-key}")
    private String key;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public ResponseEntity<?> login(@Valid LoginRequest loginRequest){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());

        authenticationManager.authenticate(token);

        //code được thực thi khi authenticate xong
        // Tận dụng api để tạo ra private-key
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
//        String key = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println("kiem tra   " + key);

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        String authenToken = Jwts.builder().subject("data").signWith(secretKey).compact();

        return new ResponseEntity<>(authenToken, HttpStatus.OK);
    }
}
