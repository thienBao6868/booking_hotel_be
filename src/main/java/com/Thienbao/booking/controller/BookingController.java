package com.Thienbao.booking.controller;

import com.Thienbao.booking.payload.request.CreateBookingRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.utils.Helper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base-path}/booking")
public class BookingController {

    @Autowired
    private Helper helper;

    //create Booking
    @PostMapping("/create-booking")
    public ResponseEntity<?> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        Long currentUserId = helper.getCurrentUserId();
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "create booking successful", request, null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    ;
    // cancelBooking
    // getBookings
    // getBookingByHotelier
    // getDetailBooking
    // paymentBooking
}
