package com.jpastudy.mybook.domain.review.dto;

import com.jpastudy.mybook.domain.book.domain.Book;
import com.jpastudy.mybook.domain.member.domain.Member;
import com.jpastudy.mybook.domain.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReviewDto {

    private Long id;
    private String content;
    private Long memberId;
    private Long bookId;
    private Integer score;

    @Builder
    public ReviewDto(Long id, Long memberId, Long bookId, String content, Integer score){
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.content = content;
        this.score = score;
    }

    public Review toEntity(Member member, Book book){
        return Review.builder()
                .member(member)
                .book(book)
                .content(content)
                .score(score)
                .build();
    }

}
