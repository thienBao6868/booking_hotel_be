package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.HotelReviewDto;
import com.Thienbao.booking.payload.request.CreateReviewRequest;

public interface ReviewServiceImp {
    HotelReviewDto createReview(CreateReviewRequest createReviewRequest, Long currentUserId);

}
