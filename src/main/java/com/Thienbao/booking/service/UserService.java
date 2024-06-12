package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.exception.NotFoundException;
import com.Thienbao.booking.mapper.UserMapper;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;



    public UserDto getUserDetail(String email){
        UserDto userDto = new UserDto();
        try {
            Optional<User> user = userRepository.findByEmail(email);

            if (user.isPresent()){
                return userDto = userMapper.userConvertToUserDto(user.get());
            }else {
                throw  new NotFoundException("User not found with email " + email);
            }
        } catch (Exception ex) {
            throw  new RuntimeException("Not get User Detail");
        }
    }

}
