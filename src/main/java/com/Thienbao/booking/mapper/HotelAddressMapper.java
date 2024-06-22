package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.HotelAddressDto;

import com.Thienbao.booking.model.HotelAddress;
import org.springframework.stereotype.Service;

@Service
public class HotelAddressMapper {
    public HotelAddressDto hotelAddressConvertTohotelAddressDto(HotelAddress hotelAddress){
        HotelAddressDto hotelAddressDto = new HotelAddressDto();

        hotelAddressDto.setStreetNumber(hotelAddress.getStreetNumber());
        hotelAddressDto.setStreetName(hotelAddress.getStreetName());
        hotelAddressDto.setDistrict(hotelAddress.getDistrict());
        hotelAddressDto.setProvince(hotelAddress.getProvince());
        hotelAddressDto.setCity(hotelAddress.getCity());
        hotelAddressDto.setCountry(hotelAddress.getCountry());
        return hotelAddressDto;
    }
}
