package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.BookingDto;
import com.Thienbao.booking.dto.BookingRoomDto;
import com.Thienbao.booking.model.Booking;
import com.Thienbao.booking.model.BookingRoom;
import com.Thienbao.booking.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingMapper {

    @Autowired
    private BookingRoomMapper bookingRoomMapper;


    public BookingDto convertToBookingDto(Booking booking, BookingRoom bookingRoom){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setHotelId(booking.getHotel().getId());
        bookingDto.setBookingDate(booking.getBookingDate());
        bookingDto.setTotalPrice(booking.getTotalPrice());
        bookingDto.setPaymentAmount(booking.getPaymentAmount());
        bookingDto.setPaymentStatus(booking.getPaymentStatus());
        bookingDto.setPaymentDate(booking.getPaymentDate());
        bookingDto.setBookingRoom(bookingRoomMapper.convertToBookingRoomDto(bookingRoom));
        return bookingDto;
    }
}
