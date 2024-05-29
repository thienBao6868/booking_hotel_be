package com.Thienbao.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "hotel_amenities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelAmenities {

    @Id
    @ManyToOne
    @JoinColumn(name = "hotel_id",referencedColumnName = "id")
    private Hotel hotel;

    @Id
    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id")
    private Amenities amenity;


}
