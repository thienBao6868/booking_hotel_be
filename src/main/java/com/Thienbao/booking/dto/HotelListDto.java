package com.Thienbao.booking.dto;

import com.Thienbao.booking.model.HotelAddress;
import lombok.Data;

import java.util.List;

@Data
public class HotelListDto extends HotelDto {
    private List<HotelImageDto> hotelImageDtoList;
    private HotelAddressDto hotelAddressDto;
    private List<HotelReviewDto> hotelReviewDtoList;
}
