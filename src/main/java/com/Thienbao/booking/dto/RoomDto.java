package com.Thienbao.booking.dto;

import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.ROOM_STATUS;
import com.Thienbao.booking.model.RoomType;
import lombok.Data;

@Data
public class RoomDto {
    private int id;
    private int roomNumber;
    private RoomTypeDto roomType;
    private double price;
    private ROOM_STATUS status;
}
