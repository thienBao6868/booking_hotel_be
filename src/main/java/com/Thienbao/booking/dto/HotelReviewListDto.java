package com.Thienbao.booking.dto;

import com.Thienbao.booking.model.ReviewReplies;
import com.Thienbao.booking.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HotelReviewListDto {
    private int id;
    private String comment;
    private LocalDateTime reviewDate;
    private UserDto user;
    private List<ReviewReplyDto> reviewReplies;
}
