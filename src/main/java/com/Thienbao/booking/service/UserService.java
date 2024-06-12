package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.mapper.UserMapper;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;



    public UserDto getUserDetail(String email){
        UserDto userDto = new UserDto();
        try {
            User user = userRepository.findByEmail(email);
            return userDto = userMapper.userConvertToUserDto(user);

        } catch (RuntimeException ex) {
            String err = ex.getMessage();
            System.out.print("Error : " + err);
        }
        return userDto;
    }

}
