package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.HotelDetailDto;
import com.Thienbao.booking.dto.HotelListDto;

import java.util.List;

public interface HotelServiceImp {
    List<HotelListDto> getHotels();
    HotelDetailDto getHotelDetail(int id);
    List<HotelDetailDto> getHotelsByUserId(Long userId);

}
