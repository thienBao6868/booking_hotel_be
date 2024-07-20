package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.HotelDetailDto;
import com.Thienbao.booking.dto.HotelListDto;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.payload.request.InsertHotelRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface HotelServiceImp {
    List<HotelListDto> getHotels();
    HotelDetailDto getHotelDetail(int id);
    List<HotelDetailDto> getHotelsByUserId(Long userId);
    Hotel insertHotel(HttpServletRequest request, InsertHotelRequest hotelRequest);

}
