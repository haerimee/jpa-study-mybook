package com.jpastudy.mybook.domain.review.web;

import com.jpastudy.mybook.domain.review.dto.ReviewDto;
import com.jpastudy.mybook.domain.review.dto.ReviewSaveRequestDto;
import com.jpastudy.mybook.domain.review.dto.ReviewSearchDto;
import com.jpastudy.mybook.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Long> registerReview(@RequestBody ReviewSaveRequestDto reviewSaveRequestDto){
        Long reviewId = reviewService.saveReview(reviewSaveRequestDto);
        return new ResponseEntity<>(reviewId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{memberId}")
    public ResponseEntity<List<ReviewSearchDto>> getReviewList(@PathVariable Long memberId){
        List<ReviewSearchDto> reviews = reviewService.findAllReviewsByMemberId(memberId);
        return ResponseEntity.ok(reviews);
    }

}
