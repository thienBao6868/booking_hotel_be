package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.HotelAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelAddressRepository extends JpaRepository<HotelAddress, Hotel> {
    Optional<HotelAddress> findByHotel(Hotel hotel);
}
