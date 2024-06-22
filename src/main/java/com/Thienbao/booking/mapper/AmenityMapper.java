package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.AmenitiesDto;
import com.Thienbao.booking.model.Amenities;
import org.springframework.stereotype.Service;

@Service
public class AmenityMapper {
    public AmenitiesDto convertToAmenitiesDto(Amenities amenity){
        AmenitiesDto amenitiesDto = new AmenitiesDto();
        amenitiesDto.setName(amenity.getName());
        amenitiesDto.setIcon(amenity.getIcon());
        return amenitiesDto;
        }
}
