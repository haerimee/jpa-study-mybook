package com.jpastudy.mybook.domain.book.domain;

import com.jpastudy.mybook.domain.review.domain.Review;
import com.jpastudy.mybook.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Book extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String isbn;

    private String image;

    private String author;

    private String publisher;

    private String link;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Book(String title, String isbn, String image, String author, String publisher, String link, String description){
        this.title = title;
        this.isbn = isbn;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.link = link;
        this.description = description;
    }
}
