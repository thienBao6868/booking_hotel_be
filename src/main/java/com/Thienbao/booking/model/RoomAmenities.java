package com.Thienbao.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "room_amenities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAmenities {

    @Id
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id")
    private Amenities amenity;

}
