package com.jpastudy.mybook.domain.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReviewSearchDetailDto {

    private Long id;
    private Long memberId;
    private Long bookId;
    private String content;
    private Integer score;

    private String title;
    private String isbn;
    private String image;
    private String author;
    private String publisher;
    private String link;

    private String createdDate;
    private String modifiedDate;

    @Builder
    public ReviewSearchDetailDto(Long id, Long memberId, Long bookId, String content, Integer score, String title, String isbn, String image, String author, String publisher, String link, String createdDate, String modifiedDate){
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.content = content;
        this.score = score;
        this.title = title;
        this.isbn = isbn;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.link = link;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
