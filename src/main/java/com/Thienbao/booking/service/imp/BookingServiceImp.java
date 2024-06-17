package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.payload.request.CreateBookingRequest;
import org.springframework.stereotype.Service;

@Service
public interface BookingServiceImp {
    boolean createBooking(CreateBookingRequest request, Long currentUser);
}
