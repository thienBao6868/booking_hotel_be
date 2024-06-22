package com.Thienbao.booking.controller;

import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.payload.request.AdminGetUserByIdUserRequest;
import com.Thienbao.booking.payload.request.UpdateHotelByAdminRequest;
import com.Thienbao.booking.payload.request.UpdateUserByAdminRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.service.AdminService;
import com.Thienbao.booking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-path}/admin")
public class AminController {

    @Autowired
    private AdminService adminService;

    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity<?> adminGetUserByIdUser(@Valid AdminGetUserByIdUserRequest adminGetUserByIdUserRequest) {

        UserDto userDto = adminService.getUserById(adminGetUserByIdUserRequest.getId());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get User By Id Successful");
        baseResponse.setData(userDto);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };

    @PutMapping("/user")
    public ResponseEntity<?> updateUserByAdmin(@Valid @RequestBody UpdateUserByAdminRequest updateUserByAdminRequest){

        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Update User By Admin successful",adminService.updateUserByAdmin(updateUserByAdminRequest),null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/hotel")
    public ResponseEntity<?> updateHotelByAdmin(@Valid @RequestBody UpdateHotelByAdminRequest request){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Update User By Admin successful",adminService.updateHotelByAdmin(request),null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
