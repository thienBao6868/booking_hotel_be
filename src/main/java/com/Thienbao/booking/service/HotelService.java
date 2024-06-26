package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.HotelDetailDto;
import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.dto.HotelListDto;
import com.Thienbao.booking.exception.NotFoundException;
import com.Thienbao.booking.mapper.HotelMapper;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.repository.HotelRepository;
import com.Thienbao.booking.service.imp.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService implements HotelServiceImp {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public List<HotelListDto> getHotels(){
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelListDto> hotelListDto = new ArrayList<>();
        for(Hotel hotel: hotels){
            hotelListDto.add(hotelMapper.hotelConvertToHotelListDto(hotel));
        }
        return hotelListDto;
    }

    @Override
    public HotelDetailDto getHotelDetail(int id){
        HotelDetailDto hotelDetailDto = new HotelDetailDto();
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new NotFoundException("Not found hotel with id"));
        return hotelMapper.hotelConvertHotelDetailDto(hotel,hotelDetailDto);
    }

    @Override
    public  List<HotelDetailDto> getHotelsByUserId(Long userId){

        HotelDetailDto hotelDetailDto = new HotelDetailDto();
        List<HotelDetailDto> hotelDetailDtoList = new ArrayList<>();
        List<Hotel> hotels = hotelRepository.findByUserId(userId);

        for (Hotel hotel: hotels)
        {
            hotelDetailDtoList.add(hotelMapper.hotelConvertHotelDetailDto(hotel,hotelDetailDto));
        }
        return hotelDetailDtoList;
    }

}
