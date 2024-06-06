package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.HotelReviewDto;
import com.Thienbao.booking.model.HotelReviews;
import org.springframework.stereotype.Service;

@Service
public class HotelReviewMapper {

    public HotelReviewDto hotelReviewConvertToHotelReviewDto(HotelReviews hotelReviews){
        HotelReviewDto hotelReviewDto = new HotelReviewDto();
        hotelReviewDto.setId(hotelReviews.getId());
        hotelReviewDto.setComment(hotelReviews.getComment());
        hotelReviewDto.setReviewDate(hotelReviews.getReviewDate());
        return hotelReviewDto;
    }

}
