package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.BookingDto;
import com.Thienbao.booking.dto.BookingListDto;
import com.Thienbao.booking.dto.BookingListOfHotelierDto;
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

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private UserMapper userMapper;




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

    public BookingListDto convertToBookingListDto(Booking booking){
        BookingListDto bookingListDto = new BookingListDto();
        bookingListDto.setBookingDate(booking.getBookingDate());
        bookingListDto.setTotalPrice(booking.getTotalPrice());
        bookingListDto.setPaymentAmount(booking.getPaymentAmount());
        bookingListDto.setPaymentStatus(booking.getPaymentStatus());
        bookingListDto.setPaymentDate(booking.getPaymentDate());
        bookingListDto.setHotel(hotelMapper.hotelConvertToHotelDto(booking.getHotel()));

        List<BookingRoom> bookingRooms = booking.getBookingRoomList();
        List<BookingRoomDto> bookingRoomDtos = new ArrayList<>();

        bookingRooms.forEach(item -> {
            bookingRoomDtos.add(bookingRoomMapper.convertToBookingRoomDto(item));
        });
        bookingListDto.setBookingRoomList(bookingRoomDtos);

        return  bookingListDto;
    }

    public BookingListOfHotelierDto convertToBookingListOfHotelierDto(Booking booking){
        BookingListOfHotelierDto bookingListOfHotelierDto = new BookingListOfHotelierDto();
        bookingListOfHotelierDto.setUser(userMapper.userConvertToUserDto(booking.getUser()));
        bookingListOfHotelierDto.setBookingDate(booking.getBookingDate());
        bookingListOfHotelierDto.setTotalPrice(booking.getTotalPrice());
        bookingListOfHotelierDto.setPaymentAmount(booking.getPaymentAmount());
        bookingListOfHotelierDto.setPaymentStatus(booking.getPaymentStatus());
        bookingListOfHotelierDto.setPaymentDate(booking.getPaymentDate());

        List<BookingRoom> bookingRooms = booking.getBookingRoomList();
        List<BookingRoomDto> bookingRoomDtos = new ArrayList<>();

        bookingRooms.forEach(item -> {
            bookingRoomDtos.add(bookingRoomMapper.convertToBookingRoomDto(item));
        });
        bookingListOfHotelierDto.setBookingRoomList(bookingRoomDtos);

        return bookingListOfHotelierDto;
    }
}
