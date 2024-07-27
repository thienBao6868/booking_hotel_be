package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage, Hotel> {
    Optional<HotelImage>  findByHotel(Hotel hotel);
}
