package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.exception.NotFoundException;
import com.Thienbao.booking.mapper.UserMapper;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.repository.UserRepository;
import com.Thienbao.booking.service.imp.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminServiceImp {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getUserById(Long id) {
        UserDto userDto = new UserDto();
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found user with id " + id));
        return userDto = userMapper.userConvertToUserDto(user);
    };
}
