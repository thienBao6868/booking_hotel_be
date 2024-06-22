package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.ReviewReplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepliesRepository extends JpaRepository<ReviewReplies,Integer> {
}
