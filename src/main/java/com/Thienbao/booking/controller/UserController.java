package com.Thienbao.booking.controller;

import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("${api.base-path}/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<?> getUserById(@RequestParam Long id){

        try{
            User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("Not found user with id"));

            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            userDto.setFullName(user.getFullName());
            userDto.setPhone(user.getPhone());
            userDto.setSex(user.getSex());
            userDto.setAddress(user.getAddress());
            userDto.setRole(user.getRole());

            List<Hotel> hotels = user.getHotels();

            List<HotelDto> hotelDtoList = new ArrayList<HotelDto>();

            for (int i = 0; i < hotels.size(); i++) {
                HotelDto hotelDto = new HotelDto();
                hotelDto.setId(hotels.get(i).getId());
                hotelDto.setName(hotels.get(i).getName());
                hotelDto.setDescription(hotels.get(i).getDescription());
                hotelDto.setPhone(hotels.get(i).getPhone());
                hotelDto.setOpenTime(hotels.get(i).getOpenTime());
                hotelDto.setCloseTime(hotels.get(i).getCloseTime());
                hotelDto.setCheckinTime(hotels.get(i).getCheckinTime());
                hotelDto.setCheckoutTime(hotels.get(i).getCheckoutTime());
                hotelDto.setRating(hotels.get(i).getRating());

                hotelDtoList.add(hotelDto);

            }

            userDto.setHotels(hotelDtoList);


            BaseResponse baseResponse = new BaseResponse();

            baseResponse.setStatusCode(200);
            baseResponse.setMessage("Success");
            baseResponse.setData(userDto);

            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

        }catch (RuntimeException ex){
            String err = ex.getMessage();
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
        }

    };
}
