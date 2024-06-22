package com.Thienbao.booking.mapper;

import com.Thienbao.booking.dto.ReviewReplyDto;
import com.Thienbao.booking.model.ReviewReplies;
import org.springframework.stereotype.Service;

@Service
public class ReviewReplyMapper {
    public ReviewReplyDto convertToReviewReplyDto(ReviewReplies reviewReply){
        ReviewReplyDto reviewReplyDto = new ReviewReplyDto();
        reviewReplyDto.setReply(reviewReply.getReplyText());
        reviewReplyDto.setReplyDate(reviewReply.getReplyDate());
        return  reviewReplyDto;
    };
}
