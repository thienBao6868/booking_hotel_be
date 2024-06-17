package com.Thienbao.booking.dto;

import com.Thienbao.booking.model.BookingRoom;
import com.Thienbao.booking.model.PAYMENT_STATUS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private int hotelId;
    private LocalDateTime bookingDate;
    private double totalPrice;
    private double paymentAmount;
    private PAYMENT_STATUS paymentStatus;
    private LocalDateTime paymentDate;
    private BookingRoomDto bookingRoom;

}
