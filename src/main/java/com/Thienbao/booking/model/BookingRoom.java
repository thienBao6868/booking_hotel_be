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
    @Column(name = "status", columnDefinition = "ENUM('PENDING','CONFIRMED','CHECKIN','CHECKOUT','CANCELLED') DEFAULT 'PENDING'")
    private BOOKING_ROOM_STATUS status;

    @Column(name = "checkin_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime checkinDate;

    @Column(name = "checkout_date",columnDefinition = "TIMESTAMP")
    private LocalDateTime checkoutDate;

}
