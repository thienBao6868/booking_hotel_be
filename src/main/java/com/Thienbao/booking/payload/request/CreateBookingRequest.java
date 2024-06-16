package com.Thienbao.booking.payload.request;

import com.Thienbao.booking.validation.ValidDateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookingRequest {

    @NotNull(message = "Id hotel not null")
    private int hotelId;

    @NotNull(message = "total price not null")
    private double totalPrice;

    @NotNull(message = "Id room not null")
    private int roomId;

    @NotBlank(message = "Checkin time cannot be blank")
    @ValidDateTimeFormat(pattern = "dd/MM/yyyy HH:mm", message = "Invalid date-time format. Expected format: dd/MM/yyyy HH:mm")
    private String checkinTime;

    @NotBlank(message = "Checkin out cannot be blank")
    @ValidDateTimeFormat(pattern = "dd/MM/yyyy HH:mm", message = "Invalid date-time format. Expected format: dd/MM/yyyy HH:mm")
    private String checkoutTime;

    @NotNull(message = "payment Amount not null")
    private double paymentAmount;
}
