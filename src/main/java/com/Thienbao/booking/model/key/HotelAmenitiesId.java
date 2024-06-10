package com.Thienbao.booking.model.key;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class HotelAmenitiesId implements Serializable {
    private int hotelId;
    private int amenityId;
}
