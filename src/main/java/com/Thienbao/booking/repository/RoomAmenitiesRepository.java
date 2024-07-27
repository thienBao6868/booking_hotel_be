package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.RoomAmenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoomAmenitiesRepository extends JpaRepository<RoomAmenities , Integer> {
    @Transactional
    void deleteByRoomId(int RoomId);
}
