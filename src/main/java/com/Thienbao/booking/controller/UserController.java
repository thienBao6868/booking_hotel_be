package com.Thienbao.booking.controller;


import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.security.DataSecurity;
import com.Thienbao.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("${api.base-path}/user")
public class UserController {

    @Autowired
    UserService userService;



    @CrossOrigin
    @GetMapping("/me")
    public ResponseEntity<?> getMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DataSecurity dataSecurity =  (DataSecurity) authentication.getPrincipal();
        String email = dataSecurity.getEmail();

        UserDto userDto = userService.getUserDetail(email);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get Current User Successful");
        baseResponse.setData(userDto);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    // Create user

    // update user

}
