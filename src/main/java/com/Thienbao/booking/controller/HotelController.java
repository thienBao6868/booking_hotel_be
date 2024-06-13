package com.Thienbao.booking.controller;

import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.repository.HotelRepository;
import com.Thienbao.booking.security.DataSecurity;
import com.Thienbao.booking.service.HotelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-path}/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/all")
    public ResponseEntity<?> getHotels(){

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get all hotel successful");
        baseResponse.setData(hotelService.getHotels());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable @Valid @Positive int id){

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get hotel by Id Successful");
        baseResponse.setData(hotelService.getHotelDetail(id));

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getHotelsByHotelier () {

        // Get id in SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DataSecurity dataSecurity = (DataSecurity) authentication.getPrincipal();
        Long currentUserId = dataSecurity.getId();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get information hotels with current Hotel_owner Successful");
        baseResponse.setData(hotelService.getHotelsByUserId(currentUserId));

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    // Create hotel
    // Update hotel
    // Create Room
    // update Room


}
