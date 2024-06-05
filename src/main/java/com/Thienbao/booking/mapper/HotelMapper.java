package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.model.Hotel;
import org.springframework.stereotype.Service;

@Service
public class HotelMapper {

    public HotelDto hotelConvertToHotelDto (Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setDescription(hotel.getDescription());
        hotelDto.setPhone(hotel.getPhone());
        hotelDto.setOpenTime(hotel.getOpenTime());
        hotelDto.setCloseTime(hotel.getCloseTime());
        hotelDto.setCheckinTime(hotel.getCheckinTime());
        hotelDto.setCheckoutTime(hotel.getCheckoutTime());
        hotelDto.setRating(hotel.getRating());
        return hotelDto;
    }
}
