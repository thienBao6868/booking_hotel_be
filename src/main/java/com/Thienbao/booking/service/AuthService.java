package com.Thienbao.booking.service;

import com.Thienbao.booking.exception.NotFoundException;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.payload.request.LogoutRequest;
import com.Thienbao.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email){
       Optional<User> user = userRepository.findByEmail(email);
       if (user.isPresent()){
           return user.get();
       }else {
           throw new NotFoundException("User not found with email");
       }
    }

    public void logout(LogoutRequest logoutRequest){
        try{

        }catch(RuntimeException e){
            System.out.println("Error logout : " + e.getMessage());
        }
    }
}
