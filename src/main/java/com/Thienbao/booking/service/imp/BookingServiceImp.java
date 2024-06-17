package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.BookingDto;
import com.Thienbao.booking.dto.BookingRoomDto;
import com.Thienbao.booking.payload.request.CreateBookingRequest;
import org.springframework.stereotype.Service;

@Service
public interface BookingServiceImp {
    BookingDto createBooking(CreateBookingRequest request, Long currentUser);
}
