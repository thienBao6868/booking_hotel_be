package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.HotelAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelAddressRepository extends JpaRepository<HotelAddress, Hotel> {
}
