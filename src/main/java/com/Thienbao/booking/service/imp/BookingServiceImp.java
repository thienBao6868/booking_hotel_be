package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.BookingDto;
import com.Thienbao.booking.dto.BookingListDto;
import com.Thienbao.booking.dto.BookingListOfHotelierDto;
import com.Thienbao.booking.dto.BookingRoomDto;
import com.Thienbao.booking.model.Booking;
import com.Thienbao.booking.payload.request.CreateBookingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingServiceImp {
    BookingDto createBooking(CreateBookingRequest request, Long currentUser);
    List<BookingListDto> getBookingsByUser(Long currentUser);

    List<BookingListOfHotelierDto> getBookingsByHotelier(Long currentUser, int hotelId);
}
