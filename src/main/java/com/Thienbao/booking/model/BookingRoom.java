package com.Thienbao.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "booking_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRoom {
    @Id
    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

    @Id
    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "id")
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BOOKING_ROOM_STATUS status = BOOKING_ROOM_STATUS.PENDING;

    @Column(name = "checkin_date")
    private LocalDateTime checkinDate;

    @Column(name = "checkout_date")
    private LocalDateTime checkoutDate;

}
