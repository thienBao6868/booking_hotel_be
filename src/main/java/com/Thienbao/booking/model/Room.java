package com.Thienbao.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_number")
    private int roomNumber;

    @ManyToOne
    @JoinColumn(name = "roomtype_id",referencedColumnName = "id")
    private RoomType roomType;

    @Column(name="description")
    private String description;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "hotel_id",referencedColumnName = "id")
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ROOM_STATUS status = ROOM_STATUS.AVAILABLE;

    @OneToMany(mappedBy = "room")
    private List<RoomImage> roomImageList;

    @OneToMany(mappedBy = "room")
    private List<BookingRoom> bookingRoomList;


    @OneToMany(mappedBy = "room")
    private List<RoomAmenities> roomAmenitiesList;

}
