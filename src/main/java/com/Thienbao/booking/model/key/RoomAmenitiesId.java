package com.Thienbao.booking.model.key;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class RoomAmenitiesId implements Serializable {
    private int roomId;
    private int amenityId;
}
