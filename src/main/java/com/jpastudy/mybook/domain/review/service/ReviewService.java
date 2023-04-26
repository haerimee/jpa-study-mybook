package com.jpastudy.mybook.domain.review.service;

import com.jpastudy.mybook.domain.book.domain.Book;
import com.jpastudy.mybook.domain.book.repository.BookRepository;
import com.jpastudy.mybook.domain.book.service.BookService;
import com.jpastudy.mybook.domain.member.domain.Member;
import com.jpastudy.mybook.domain.member.repository.MemberRepository;
import com.jpastudy.mybook.domain.review.domain.Review;
import com.jpastudy.mybook.domain.review.dto.*;
import com.jpastudy.mybook.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    private final BookService bookService;

    public Long save(ReviewSaveRequestDto reviewSaveRequestDto){
        Member member = memberRepository.findById(reviewSaveRequestDto.getMemberId());

        // 책 중복 체크
        Long newBookId;
        Optional<Book> foundBook = bookRepository.findByIsbn(reviewSaveRequestDto.getBook().getIsbn());
        if(foundBook.isEmpty()){
            // 책 먼저 저장
            newBookId = bookService.saveBook(reviewSaveRequestDto.getBook());
        }else{
            newBookId = foundBook.get().getId();
        }
        Book book = bookRepository.findById(newBookId).orElseThrow(() -> new IllegalArgumentException("해당 책이 존재하지 않습니다."));;
        
        // 리뷰 저장
        Review newReview = reviewSaveRequestDto.toEntity(member, book);
        reviewRepository.save(newReview);

        return newReview.getId();
    }

    public Page<ReviewSearchDto> findAllReviewsByMemberId(Long memberId, Pageable pageable){
        Page<Review> reviewList = reviewRepository.findAllByMemberId(memberId, pageable);
        Page<ReviewSearchDto> reviewsDtoList = new ReviewSearchDto().toDtoList(reviewList);
        
        return reviewsDtoList;
        //return reviewRepository.findAllReviewsByMemberId(memberId);
    }

    public ReviewSearchDetailDto findByReviewIdWithBookInfo(Long reviewId){
        return reviewRepository.findByReviewIdWithBookInfo(reviewId);
    }

    public Long update(Long reviewId, ReviewUpdateRequestDto reviewUpdateRequestDto){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("해당 기록이 존재하지 않습니다."));
        review.update(reviewUpdateRequestDto.getContent(), reviewUpdateRequestDto.getScore());

        return review.getId();
    }

    public void delete(Long reviewId){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("해당 기록이 존재하지 않습니다."));
        reviewRepository.delete(review);
    }

}
