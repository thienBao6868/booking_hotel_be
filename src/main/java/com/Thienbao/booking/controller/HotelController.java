package com.Thienbao.booking.controller;

import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.payload.request.InsertHotelRequest;
import com.Thienbao.booking.payload.request.UpdateHotelRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.repository.HotelRepository;
import com.Thienbao.booking.security.DataSecurity;
import com.Thienbao.booking.service.HotelService;
import com.Thienbao.booking.service.imp.HotelServiceImp;
import com.Thienbao.booking.utils.Helper;
import jakarta.servlet.http.HttpServletRequest;
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
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelServiceImp hotelServiceImp;
    @Autowired
    private Helper helper;

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
    @PostMapping
    public ResponseEntity<?> insertHotel(HttpServletRequest request, InsertHotelRequest hotelRequest){

        System.out.println(hotelRequest.getCheckOutTime());
        Hotel hotel = hotelServiceImp.insertHotel(request,hotelRequest);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }


    @PutMapping(value ="/{id}",consumes = {"multipart/form-data"})
    public  ResponseEntity<?> updateHotel(@PathVariable Long id,HttpServletRequest request,@Valid UpdateHotelRequest updateHotelRequest){

        updateHotelRequest.setHotelID(Math.toIntExact(id));
        System.out.println( id);
        Hotel hotel = hotelServiceImp.updateHotel(request,updateHotelRequest);

        return  new ResponseEntity<>(hotel,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long id){

        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Delete Succesfull");
    }
}
