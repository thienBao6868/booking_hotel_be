package com.Thienbao.booking.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {
    @Value("${jwt.private-key}")
    private String key;

    private long plusTime = 60 * 60 * 1000 * 12;
    public String generateToken(String data) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));

        Date currentDate = new Date();
        long futureTime = currentDate.getTime() + plusTime;
        Date futureDate = new Date(futureTime);

        return Jwts.builder()
                .subject(data)
                .signWith(secretKey)
                .expiration(futureDate)
                .compact();
    }

    public boolean decodeToken(String token){
        boolean isSucess = false;
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        try{
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            isSucess=true;
        }catch(RuntimeException ex){
            System.out.println("Error decode token : " + ex.getMessage());
        }
        return isSucess;
    }
}
