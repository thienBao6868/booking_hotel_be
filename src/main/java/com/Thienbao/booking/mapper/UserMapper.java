package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserMapper {

    @Autowired HotelMapper hotelMapper;
    public UserDto userConvertToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFullName());
        userDto.setPhone(user.getPhone());
        userDto.setSex(user.getSex());
        userDto.setAddress(user.getAddress());
        userDto.setRole(user.getRole());
        List<Hotel> hotels = user.getHotels();
        List<HotelDto> hotelDtoList = new ArrayList<>();

        for(Hotel hotel : hotels){
            hotelDtoList.add(hotelMapper.hotelConvertToHotelDto(hotel));
        }
        userDto.setHotels(hotelDtoList);

        return userDto;
    }
}
