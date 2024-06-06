package com.Thienbao.booking.service;

import com.Thienbao.booking.model.User;
import com.Thienbao.booking.payload.request.LogoutRequest;
import com.Thienbao.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void logout(LogoutRequest logoutRequest){
        try{

        }catch(RuntimeException e){
            System.out.println("Error logout : " + e.getMessage());
        }
    }
}
