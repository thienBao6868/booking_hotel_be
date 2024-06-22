package com.Thienbao.booking.controller;

import com.Thienbao.booking.payload.request.CancelBookingRequest;
import com.Thienbao.booking.payload.request.CreateBookingRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.service.imp.BookingServiceImp;
import com.Thienbao.booking.utils.Helper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-path}/booking")
public class BookingController {

    @Autowired
    private Helper helper;

    @Autowired
    private BookingServiceImp bookingServiceImp;

    //create Booking
    @PostMapping("/create-booking")
    public ResponseEntity<?> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        Long currentUserId = helper.getCurrentUserId();
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "create booking successful",bookingServiceImp.createBooking(request,currentUserId), null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };



    // getBookings by User
    @GetMapping("/user")
    public ResponseEntity<?> getBookingByUser(){
        Long currentUserId = helper.getCurrentUserId();
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get bookings by User successful",bookingServiceImp.getBookingsByUser(currentUserId), null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };

    // getBookingByHotelier
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<?> getBookingByHotelier(@PathVariable int hotelId){
        Long currentUserId = helper.getCurrentUserId();
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get bookings by Hotelier successful",bookingServiceImp.getBookingsByHotelier(currentUserId,hotelId), null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };

    // getDetailBooking
    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getDetailBooking(@PathVariable int bookingId){
        Long currentUserId = helper.getCurrentUserId();
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get detail booking successful",bookingServiceImp.getDetailBooking(currentUserId,bookingId), null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };

    // cancelBooking
    @PutMapping("")
    public ResponseEntity<?> cancelBooking(@Valid @RequestBody CancelBookingRequest request){
        Long currentUserId = helper.getCurrentUserId();
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "cancel booking successful",bookingServiceImp.cancelBooking(request), null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };

    // update Booking




}
