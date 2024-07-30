package com.Thienbao.booking.payload.request;

import com.Thienbao.booking.model.ROOM_STATUS;
import lombok.Data;

@Data
public class UpdateRoomRequest {
    private int numberRoom;
    private String description;
    private int idRoomType;
    private double price ;
    private int idHotel;
    private ROOM_STATUS status;
}
