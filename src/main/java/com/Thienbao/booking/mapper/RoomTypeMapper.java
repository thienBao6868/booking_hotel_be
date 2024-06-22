package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.RoomTypeDto;
import com.Thienbao.booking.model.RoomType;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeMapper {
    public RoomTypeDto roomTypeConvertToRoomTypeDto(RoomType roomType){
        RoomTypeDto roomTypeDto = new RoomTypeDto();
        roomTypeDto.setId(roomType.getId());
        roomTypeDto.setName(roomType.getName());
        return roomTypeDto;
    }
}
