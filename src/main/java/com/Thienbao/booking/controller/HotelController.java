package com.Thienbao.booking.controller;

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
        return new ResponseEntity<>(hotelService.gethotels(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable @Valid @Positive int id){
        return new ResponseEntity<>(hotelService.getHotelDetail(id), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getHotelsByHotelier () {

        // Get id in SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DataSecurity dataSecurity = (DataSecurity) authentication.getPrincipal();
        Long currentUserId = dataSecurity.getId();


        return new ResponseEntity<>(hotelService.getHotelsByUserId(currentUserId), HttpStatus.OK);

    }

    // Create hotel
    // Update hotel
    // Create Room
    // update Room


}
