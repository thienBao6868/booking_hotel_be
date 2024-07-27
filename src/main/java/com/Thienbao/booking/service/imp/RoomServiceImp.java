package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.RoomDto;
import com.Thienbao.booking.model.Room;
import com.Thienbao.booking.payload.request.InsertRoomRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface RoomServiceImp {
    List<RoomDto> getAllRoom();
    Room insertRoom(HttpServletRequest request, InsertRoomRequest insertRoomRequest);
}
