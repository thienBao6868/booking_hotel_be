package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.RoomAmenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoomAmenitiesRepository extends JpaRepository<RoomAmenities , Integer> {
    @Transactional
    void deleteByRoomId(int RoomId);
    @Transactional
    List <RoomAmenities> findByRoomId(int roomId);
}
