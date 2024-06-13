package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.payload.request.CreateUserRequest;

public interface UserServiceImp {
    UserDto getUserDetail(String email);
    UserDto createUser(CreateUserRequest createUserRequest);
}
