package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.HotelReviewDto;
import com.Thienbao.booking.exception.NotFoundException;
import com.Thienbao.booking.exception.UserAlreadyReviewException;
import com.Thienbao.booking.mapper.HotelReviewMapper;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.HotelReviews;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.payload.request.CreateReplyRequest;
import com.Thienbao.booking.payload.request.CreateReviewRequest;
import com.Thienbao.booking.repository.HotelRepository;
import com.Thienbao.booking.repository.HotelReviewRepository;
import com.Thienbao.booking.service.imp.ReviewServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public HotelReviewDto createReview(CreateReviewRequest createReviewRequest, Long currentUserId) {

        Hotel hotel = hotelRepository.findById(createReviewRequest.getHotelId()).orElseThrow(()-> new NotFoundException("Not found Hotel with Id: " + createReviewRequest.getHotelId()));

        if(Objects.equals(hotel.getUser().getId(), currentUserId)) throw new UserAlreadyReviewException("Users are not allowed to review their own hotels");

        List<HotelReviews> hotelReviews = hotel.getHotelReviews();

        if(hotelReviews != null){
            for (HotelReviews hotelReview: hotelReviews){
               if( hotelReview.getUser().getId().equals(currentUserId)) throw new UserAlreadyReviewException("Users can only review that hotel once");
            }
        }
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

    }

    @Override
    public boolean createReply(CreateReplyRequest createReplyRequest, Long currentUserId) {



        return false;
    }

    ;
}
