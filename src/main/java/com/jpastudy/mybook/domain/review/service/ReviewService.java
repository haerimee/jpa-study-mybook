package com.jpastudy.mybook.domain.review.service;

import com.jpastudy.mybook.domain.book.domain.Book;
import com.jpastudy.mybook.domain.book.repository.BookRepository;
import com.jpastudy.mybook.domain.book.service.BookService;
import com.jpastudy.mybook.domain.member.domain.Member;
import com.jpastudy.mybook.domain.member.repository.MemberRepository;
import com.jpastudy.mybook.domain.review.domain.Review;
import com.jpastudy.mybook.domain.review.dto.ReviewDto;
import com.jpastudy.mybook.domain.review.dto.ReviewSaveRequestDto;
import com.jpastudy.mybook.domain.review.dto.ReviewSearchDto;
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

    private final BookService bookService;

    public Long saveReview(ReviewSaveRequestDto reviewSaveRequestDto){
        Member member = memberRepository.findById(reviewSaveRequestDto.getMemberId());
        
        // 책 먼저 저장
        Long newBookId = bookService.saveBook(reviewSaveRequestDto.getBook());
        Book book = bookRepository.findByBookId(newBookId);
        
        // 리뷰 저장
        Review newReview = reviewSaveRequestDto.toEntity(member, book);
        reviewRepository.save(newReview);

        return newReview.getId();
    }

    public List<ReviewSearchDto> findAllReviewsByMemberId(Long memberId){
        return reviewRepository.findAllReviewsByMemberId(memberId);
    }

}
