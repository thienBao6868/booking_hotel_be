package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.RoomImage;
import com.Thienbao.booking.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface RoomImageRepository extends JpaRepository<RoomImage , Integer> {
    @Transactional
    void deleteByRoomId(int roomId);
    @Transactional
    List<RoomImage> findByRoomId(int roomId);
}
