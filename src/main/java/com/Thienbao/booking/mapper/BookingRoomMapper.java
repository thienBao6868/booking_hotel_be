package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.BookingRoomDto;
import com.Thienbao.booking.model.BookingRoom;
import com.Thienbao.booking.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingRoomMapper {

    @Autowired
    private RoomMapper roomMapper;
    public BookingRoomDto convertToBookingRoomDto(BookingRoom bookingRoom){

        BookingRoomDto bookingRoomDto = new BookingRoomDto();
        bookingRoomDto.setStatus(bookingRoom.getStatus());
        bookingRoomDto.setCheckinDate(bookingRoom.getCheckinDate());
        bookingRoomDto.setCheckoutDate(bookingRoom.getCheckoutDate());
        bookingRoomDto.setRoom(roomMapper.roomConvertToRoomDto(bookingRoom.getRoom()));
        return  bookingRoomDto;
    };
}
