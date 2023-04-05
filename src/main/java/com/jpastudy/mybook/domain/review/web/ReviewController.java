package com.jpastudy.mybook.domain.review.web;

import com.jpastudy.mybook.domain.review.domain.Review;
import com.jpastudy.mybook.domain.review.dto.ReviewDto;
import com.jpastudy.mybook.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "/new")
    public ResponseEntity<Long> registerReview(@RequestBody ReviewDto reviewDto){
        Long reviewId = reviewService.saveReview(reviewDto);
        return new ResponseEntity<>(reviewId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{memberId}")
    public ResponseEntity<List<ReviewDto>> getReviewList(@PathVariable Long memberId){
        List<ReviewDto> reviews = reviewService.findAllReviewsByMemberId(memberId);
        return ResponseEntity.ok(reviews);
    }

}
