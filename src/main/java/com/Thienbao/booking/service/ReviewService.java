package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.HotelReviewDto;
import com.Thienbao.booking.dto.HotelReviewListDto;
import com.Thienbao.booking.dto.ReviewReplyDto;
import com.Thienbao.booking.exception.*;
import com.Thienbao.booking.mapper.HotelReviewMapper;
import com.Thienbao.booking.mapper.ReviewReplyMapper;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.HotelReviews;
import com.Thienbao.booking.model.ReviewReplies;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.payload.request.CreateReplyRequest;
import com.Thienbao.booking.payload.request.CreateReviewRequest;
import com.Thienbao.booking.repository.HotelRepository;
import com.Thienbao.booking.repository.HotelReviewRepository;
import com.Thienbao.booking.repository.ReviewRepliesRepository;
import com.Thienbao.booking.service.imp.ReviewServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ReviewService implements ReviewServiceImp {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelReviewRepository hotelReviewRepository;

    @Autowired
    private HotelReviewMapper hotelReviewMapper;

    @Autowired
    private ReviewRepliesRepository reviewRepliesRepository;

    @Autowired
    private ReviewReplyMapper reviewReplyMapper;

    @Override
    public HotelReviewDto createReview(CreateReviewRequest createReviewRequest, Long currentUserId) {

        Hotel hotel = hotelRepository.findById(createReviewRequest.getHotelId()).orElseThrow(() -> new NotFoundException("Not found Hotel with Id: " + createReviewRequest.getHotelId()));

        if (Objects.equals(hotel.getUser().getId(), currentUserId))
            throw new UserAlreadyReviewException("Users are not allowed to review their own hotels");

        List<HotelReviews> hotelReviews = hotel.getHotelReviews();

        if (hotelReviews != null) {
            for (HotelReviews hotelReview : hotelReviews) {
                if (hotelReview.getUser().getId().equals(currentUserId))
                    throw new UserAlreadyReviewException("Users can only review that hotel once");
            }
        }
        try {
            User user = new User();
            user.setId(currentUserId);
            Hotel newHotel = new Hotel();
            newHotel.setId(createReviewRequest.getHotelId());

            HotelReviews hotelReview = new HotelReviews();
            hotelReview.setComment(createReviewRequest.getComment());
            hotelReview.setUser(user);
            hotelReview.setHotel(hotel);

            HotelReviews newHotelReview = hotelReviewRepository.save(hotelReview);

            return hotelReviewMapper.hotelReviewConvertToHotelReviewDto(newHotelReview);
        }catch (Exception ex){
            throw new CreateException("Error create review: " + ex.getMessage());
        }
    }

    @Override
    public ReviewReplyDto createReply(CreateReplyRequest createReplyRequest, Long currentUserId) {

        HotelReviews hotelReviews = hotelReviewRepository.findById(createReplyRequest.getReviewId()).orElseThrow(() -> new NotFoundException("Not found review with Id: " + createReplyRequest.getReviewId()));

        if(!currentUserId.equals(hotelReviews.getHotel().getUser().getId())) throw new UserAlreadyReplyException("The hotel owner has just responded");

        if(!hotelReviews.getReviewReplies().isEmpty()) throw new ReplyAlreadyExistsException("Only responded once");

        try {
            User user = new User();
            user.setId(currentUserId);

            HotelReviews newHotelReviews = new HotelReviews();
            newHotelReviews.setId(createReplyRequest.getReviewId());

            ReviewReplies reviewReply = new ReviewReplies();
            reviewReply.setReplyText(createReplyRequest.getReply());
            reviewReply.setUser(user);
            reviewReply.setHotelReview(newHotelReviews);

            return reviewReplyMapper.convertToReviewReplyDto(reviewRepliesRepository.save(reviewReply));


        }catch (Exception ex){
            throw new CreateException("Error create reply: " + ex.getMessage());
        }
    }

    @Override
    public List<HotelReviewListDto> getReviewsByHotelier(int hotelId, Long currentUserId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new NotFoundException("Not found Hotel with Id: " + hotelId));
        if(!currentUserId.equals(hotel.getUser().getId())) throw new UserAlreadyReviewException("The user must be the owner of the hotel");

        try{
            List<HotelReviews> hotelReviews = hotel.getHotelReviews();
            List<HotelReviewListDto> hotelReviewListDtoList = new ArrayList<>();

            hotelReviews.forEach(item ->{
                hotelReviewListDtoList.add(hotelReviewMapper.hotelReviewConvertToHotelReviewListDto(item));
            });
            return hotelReviewListDtoList;
        }catch (Exception ex){
            throw new NotFoundException("Error get Reviews with hotelier: "+ ex.getMessage());
        }

    };



}
