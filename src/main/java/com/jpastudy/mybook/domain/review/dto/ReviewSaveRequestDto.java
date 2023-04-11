package com.jpastudy.mybook.domain.review.dto;

import com.jpastudy.mybook.domain.book.domain.Book;
import com.jpastudy.mybook.domain.book.dto.BookDto;
import com.jpastudy.mybook.domain.member.domain.Member;
import com.jpastudy.mybook.domain.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewSaveRequestDto {

    private String content;
    private Long memberId;
    private Long bookId;
    private Integer score;
    private BookDto book;

    public Review toEntity(Member member, Book book){
        return Review.builder()
                .member(member)
                .book(book)
                .content(content)
                .score(score)
                .build();
    }
}
