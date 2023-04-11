package com.jpastudy.mybook.domain.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReviewSearchDto {

    private Long id;
    private Long memberId;
    private Long bookId;
    private String content;
    private Integer score;

    private String title;
    private String isbn;
    private String image;

    @Builder
    public ReviewSearchDto(Long id, Long memberId, Long bookId, String content, Integer score, String title, String isbn, String image){
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.content = content;
        this.score = score;
        this.title = title;
        this.isbn = isbn;
        this.image = image;
    }

}
