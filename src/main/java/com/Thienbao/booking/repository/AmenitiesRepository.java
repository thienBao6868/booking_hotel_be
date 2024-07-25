package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AmenitiesRepository extends JpaRepository<Amenities , Integer> {
}
