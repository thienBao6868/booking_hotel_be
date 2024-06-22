package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.HotelReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelReviewRepository extends JpaRepository<HotelReviews,Integer> {
}
