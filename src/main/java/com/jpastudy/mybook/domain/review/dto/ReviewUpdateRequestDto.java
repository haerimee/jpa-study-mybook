package com.jpastudy.mybook.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {

    private String content;
    private Integer score;

    @Builder
    public ReviewUpdateRequestDto(String content, Integer score){
        this.content = content;
        this.score = score;
    }
}
