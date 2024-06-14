package com.Thienbao.booking.controller;

import com.Thienbao.booking.payload.request.CreateReplyRequest;
import com.Thienbao.booking.payload.request.CreateReviewRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.security.DataSecurity;
import com.Thienbao.booking.service.imp.ReviewServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base-path}/review")
public class ReviewController {

    @Autowired
    private ReviewServiceImp reviewServiceImp;

    @PostMapping("")
    public ResponseEntity<?> createReview(@RequestBody @Valid CreateReviewRequest createReviewRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DataSecurity dataSecurity = (DataSecurity) authentication.getPrincipal();
        Long currenUserId = dataSecurity.getId();


        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.OK.value());
        baseResponse.setMessage("Create Review successful");
        baseResponse.setData(reviewServiceImp.createReview(createReviewRequest,currenUserId));


      return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };

    @PostMapping("/reply")
    public ResponseEntity<?> createReply(@RequestBody CreateReplyRequest createReplyRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DataSecurity dataSecurity = (DataSecurity) authentication.getPrincipal();
        Long currenUserId = dataSecurity.getId();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.OK.value());
        baseResponse.setMessage("Create Reply successful");
        baseResponse.setData(reviewServiceImp.createReply(createReplyRequest,currenUserId));

      return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };

}
