package com.Thienbao.booking.controller;

import com.Thienbao.booking.payload.request.CreateReplyRequest;
import com.Thienbao.booking.payload.request.CreateReviewRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.security.DataSecurity;
import com.Thienbao.booking.service.imp.ReviewServiceImp;
import com.Thienbao.booking.utils.Helper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-path}/review")
public class ReviewController {

    @Autowired
    private ReviewServiceImp reviewServiceImp;

    @Autowired
    private Helper helper;

    @PostMapping("")
    public ResponseEntity<?> createReview(@RequestBody @Valid CreateReviewRequest createReviewRequest) {
        Long currenUserId = helper.getCurrentUserId();
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Create Review successful", reviewServiceImp.createReview(createReviewRequest, currenUserId), null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };

    @PostMapping("/reply")
    public ResponseEntity<?> createReply(@Valid @RequestBody CreateReplyRequest createReplyRequest) {
        Long currenUserId = helper.getCurrentUserId();
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Create Reply successful", reviewServiceImp.createReply(createReplyRequest, currenUserId), null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };

    @GetMapping("/hotelier/{hotelId}")
    public ResponseEntity<?> getReviewsByHotelier(@PathVariable int hotelId) {
        Long currenUserId = helper.getCurrentUserId();
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get reviews by hotelier successful", currenUserId , null);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };
}
