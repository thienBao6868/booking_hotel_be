package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.HotelReviewDto;
import com.Thienbao.booking.dto.HotelReviewListDto;
import com.Thienbao.booking.dto.ReviewReplyDto;
import com.Thienbao.booking.payload.request.CreateReplyRequest;
import com.Thienbao.booking.payload.request.CreateReviewRequest;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ReviewServiceImp {
    HotelReviewDto createReview(CreateReviewRequest createReviewRequest, Long currentUserId);
    ReviewReplyDto createReply(CreateReplyRequest createReplyRequest, Long currentUserId);

    List<HotelReviewListDto> getReviewsByHotelier(int hotelId, Long currentUserId);

}
