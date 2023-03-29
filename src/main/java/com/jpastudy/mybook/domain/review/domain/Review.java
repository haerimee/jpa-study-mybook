package com.jpastudy.mybook.domain.review.domain;

import com.jpastudy.mybook.domain.book.domain.Book;
import com.jpastudy.mybook.domain.member.domain.Member;
import com.jpastudy.mybook.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id",foreignKey = @ForeignKey(name = "fk_review_to_member"))
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id",foreignKey = @ForeignKey(name = "fk_review_to_book"))
    private Book book;

    private Integer score;
}
