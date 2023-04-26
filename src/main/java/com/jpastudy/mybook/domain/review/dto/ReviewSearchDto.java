package com.jpastudy.mybook.domain.review.dto;

import com.jpastudy.mybook.domain.book.dto.BookDto;
import com.jpastudy.mybook.domain.review.domain.Review;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

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
    private String author;

    private String createdDate;
    private String modifiedDate;

    @Builder
    public ReviewSearchDto(Long id, Long memberId, Long bookId, String content, Integer score, String title, String isbn, String image, String author, String createdDate, String modifiedDate){
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.content = content;
        this.score = score;
        this.title = title;
        this.isbn = isbn;
        this.image = image;
        this.author = author;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
    public Page<ReviewSearchDto> toDtoList(Page<Review> reviewList){
        Page<ReviewSearchDto> reviewDtoList = reviewList.map(m ->
                ReviewSearchDto.builder()
                .id(m.getId())
                .memberId(m.getMember().getId())
                .bookId(m.getBook().getId())
                .content(m.getContent())
                .score(m.getScore())
                .title(m.getBook().getTitle())
                .isbn(m.getBook().getIsbn())
                .image(m.getBook().getImage())
                .author(m.getBook().getAuthor())
                .createdDate(m.getCreatedDate())
                .modifiedDate(m.getModifiedDate())
                .build());
        return reviewDtoList;
    }

}
