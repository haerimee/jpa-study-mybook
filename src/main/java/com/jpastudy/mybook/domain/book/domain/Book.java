package com.jpastudy.mybook.domain.book.domain;

import com.jpastudy.mybook.domain.review.domain.Review;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer isbn;

    private String thumbnail;

    private String author;

    private String publisher;

    @Lob
    private String description;

    private Integer score;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();
}
