package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.RoomDto;
import com.Thienbao.booking.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {
    @Autowired
    RoomTypeMapper roomTypeMapper;
    public RoomDto roomConvertToRoomDto(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setPrice(room.getPrice());
        roomDto.setStatus(room.getStatus());
        roomDto.setRoomType(roomTypeMapper.roomTypeConvertToRoomTypeDto(room.getRoomType()));
        return roomDto;
    }
}
