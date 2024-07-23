package com.Thienbao.booking.dto;

import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.ROOM_STATUS;
import com.Thienbao.booking.model.RoomType;
import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    private int id;
    private String nameHotel;
    private int roomNumber;
    private List<RoomTypeDto> roomType;
    private double price;
    private List<AmenitiesDto> amenities ;
    private List<String> image ;
    private ROOM_STATUS status;
}
