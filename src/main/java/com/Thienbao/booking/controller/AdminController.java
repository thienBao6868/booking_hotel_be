package com.Thienbao.booking.controller;

import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.payload.request.AdminGetUserByEmailRequest;
import com.Thienbao.booking.payload.request.AdminGetUserByIdUserRequest;
import com.Thienbao.booking.payload.request.UpdateHotelByAdminRequest;
import com.Thienbao.booking.payload.request.UpdateUserByAdminRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.service.AdminService;
import com.Thienbao.booking.service.imp.AdminServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-path}/admin")
public class AdminController {

//    @Autowired
//    private AdminService adminService;

    @Autowired
    private AdminServiceImp adminServiceImp;

    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity<?> adminGetUserByIdUser(@Valid AdminGetUserByIdUserRequest adminGetUserByIdUserRequest) {

        UserDto userDto = adminServiceImp.getUserById(adminGetUserByIdUserRequest.getId());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get User By Id Successful");
        baseResponse.setData(userDto);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/user/find-user-by-email")
    public ResponseEntity<?> adminGetUserByEmail(@Valid AdminGetUserByEmailRequest adminGetUserByEmailRequest){
        UserDto userDto = adminServiceImp.getUserByEmail(adminGetUserByEmailRequest.getEmail());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get User by Email Successful.");
        baseResponse.setData(userDto);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUserByAdmin(@Valid @RequestBody UpdateUserByAdminRequest updateUserByAdminRequest){

        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Update User By Admin successful",adminServiceImp.updateUserByAdmin(updateUserByAdminRequest),null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/hotel")
    public ResponseEntity<?> updateHotelByAdmin(@Valid @RequestBody UpdateHotelByAdminRequest request){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Update User By Admin successful",adminServiceImp.updateHotelByAdmin(request),null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    //Delete user (Ban user)
    @DeleteMapping("/user/ban")
    public ResponseEntity<?> banUserByAdmin(@Valid AdminGetUserByIdUserRequest adminGetUserByIdUserRequest){
        String message = adminServiceImp.banUserByAdmin(adminGetUserByIdUserRequest.getId());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(message);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/user/unban")
    public ResponseEntity<?> unbanUserByAdmin(@Valid AdminGetUserByIdUserRequest adminGetUserByIdUserRequest){
        String message = adminServiceImp.unbanUserByAdmin(adminGetUserByIdUserRequest.getId());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(message);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
