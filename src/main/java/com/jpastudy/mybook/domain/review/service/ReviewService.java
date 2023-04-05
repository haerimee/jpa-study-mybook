package com.jpastudy.mybook.domain.review.service;

import com.jpastudy.mybook.domain.book.domain.Book;
import com.jpastudy.mybook.domain.book.repository.BookRepository;
import com.jpastudy.mybook.domain.member.domain.Member;
import com.jpastudy.mybook.domain.member.repository.MemberRepository;
import com.jpastudy.mybook.domain.review.domain.Review;
import com.jpastudy.mybook.domain.review.dto.ReviewDto;
import com.jpastudy.mybook.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public Long saveReview(ReviewDto reviewDto){
        Member member = memberRepository.findById(reviewDto.getMemberId());
        Book book = bookRepository.findByBookId(reviewDto.getBookId());
        Review newReview = reviewDto.toEntity(member, book);

        // 저장
        reviewRepository.save(newReview);
        return newReview.getId();
    }

    public List<ReviewDto> findAllReviewsByMemberId(Long memberId){
        return reviewRepository.findAllReviewsByMemberId(memberId);
    }
}
