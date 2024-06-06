package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.dto.HotelImageDto;
import com.Thienbao.booking.dto.HotelListDto;
import com.Thienbao.booking.dto.HotelReviewDto;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.HotelImage;
import com.Thienbao.booking.model.HotelReviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelMapper {

    @Autowired
    HotelImageMapper hotelImageMapper;

    @Autowired
    HotelAddressMapper hotelAddressMapper;

    @Autowired
    HotelReviewMapper hotelReviewMapper;

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

    public HotelListDto hotelConvertToHotelListDto(Hotel hotel){
        HotelListDto hotelListDto = new HotelListDto();
        hotelListDto.setId(hotel.getId());
        hotelListDto.setName(hotel.getName());
        hotelListDto.setDescription(hotel.getDescription());
        hotelListDto.setPhone(hotel.getPhone());
        hotelListDto.setOpenTime(hotel.getOpenTime());
        hotelListDto.setCloseTime(hotel.getCloseTime());
        hotelListDto.setCheckinTime(hotel.getCheckinTime());
        hotelListDto.setCheckoutTime(hotel.getCheckoutTime());
        hotelListDto.setRating(hotel.getRating());

        List<HotelImage> hotelImageList = hotel.getHotelImages();
        List<HotelImageDto> hotelImageDtoList = new ArrayList<>();
        for(HotelImage hotelImage: hotelImageList){
            hotelImageDtoList.add(hotelImageMapper.HotelImageConvertToHotelImageDto(hotelImage));
        }
        hotelListDto.setHotelImageDtoList(hotelImageDtoList);

        hotelListDto.setHotelAddressDto(hotelAddressMapper.hotelAddressConvertTohotelAddressDto(hotel.getHotelAddress()));

        List<HotelReviews> hotelReviewsList = hotel.getHotelReviews();
        List<HotelReviewDto> hotelReviewDtoList = new ArrayList<>();
        for(HotelReviews hotelReview: hotelReviewsList){
            hotelReviewDtoList.add(hotelReviewMapper.hotelReviewConvertToHotelReviewDto(hotelReview));
        }
        hotelListDto.setHotelReviewDtoList(hotelReviewDtoList);


        return hotelListDto;
    }
}
