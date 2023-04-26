package com.jpastudy.mybook.domain.review.web;

import com.jpastudy.mybook.domain.review.dto.*;
import com.jpastudy.mybook.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "/api/review")
    public ResponseEntity<Long> saveReview(@RequestBody ReviewSaveRequestDto reviewSaveRequestDto){
        Long reviewId = reviewService.save(reviewSaveRequestDto);
        return new ResponseEntity<>(reviewId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/review/all/{memberId}")
    public ResponseEntity<Page<ReviewSearchDto>> getReviewList(@PathVariable Long memberId, @PageableDefault(size = 10) Pageable pageable){
        Page<ReviewSearchDto> reviews = reviewService.findAllReviewsByMemberId(memberId, pageable);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping(value = "/api/review/{reviewId}")
    public ResponseEntity<ReviewSearchDetailDto> getReviewByReviewIdWithBookInfo(@PathVariable Long reviewId){
        ReviewSearchDetailDto review = reviewService.findByReviewIdWithBookInfo(reviewId);
        return ResponseEntity.ok(review);
    }

    @PutMapping(value = "/api/review/{reviewId}")
    public ResponseEntity<Long> updateReview(@PathVariable Long reviewId, @RequestBody ReviewUpdateRequestDto reviewUpdateRequestDto){
        return new ResponseEntity<>(reviewService.update(reviewId, reviewUpdateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/review/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId){
        reviewService.delete(reviewId);
    }

}
