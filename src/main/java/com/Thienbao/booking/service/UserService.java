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
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserDto getUserById(Long id) {
        UserDto userDto = new UserDto();
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user with id"));
            return userDto = userMapper.userConvertToUserDto(user);

        } catch (RuntimeException ex) {
            String err = ex.getMessage();
            System.out.print("Error : " + err);
        }
        return userDto;
    }

    ;
}
