package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.*;
import com.Thienbao.booking.model.Booking;
import com.Thienbao.booking.payload.request.CancelBookingRequest;
import com.Thienbao.booking.payload.request.CreateBookingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingServiceImp {
    BookingDto createBooking(CreateBookingRequest request, Long currentUserId);
    List<BookingListDto> getBookingsByUser(Long currentUserId);
    List<BookingListOfHotelierDto> getBookingsByHotelier(Long currentUserId, int hotelId);

    BookingDetailDto getDetailBooking(Long currentUserId, int bookingId);

    boolean cancelBooking(CancelBookingRequest request);
}
