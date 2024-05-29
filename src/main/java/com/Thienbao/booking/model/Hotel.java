package com.Thienbao.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity (name = "hotels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="phone")
    private String phone;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @Column(name = "checkin_time")
    private LocalTime checkinTime;

    @Column(name = "checkout_time")
    private LocalTime checkoutTime;

    @Column(name = "rating")
    private BigDecimal rating;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
    private HotelAddress HotelAddress;

    @OneToMany(mappedBy = "hotel")
    private List<HotelImage> hotelImages;

    @OneToMany(mappedBy = "hotel")
    private List<HotelReviews> hotelReviews;

    @OneToMany(mappedBy = "hotel")
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "hotel")
    private List<Room> roomList;

    @OneToMany(mappedBy = "hotel")
    private List<HotelAmenities> hotelAmenitiesList;
}
