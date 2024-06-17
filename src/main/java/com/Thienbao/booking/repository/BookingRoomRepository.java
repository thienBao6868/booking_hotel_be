package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.BookingRoom;
import com.Thienbao.booking.model.key.BookingRoomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRoomRepository extends JpaRepository<BookingRoom, BookingRoomId> {
}
