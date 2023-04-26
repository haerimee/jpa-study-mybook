package com.jpastudy.mybook.domain.review.repository;

import com.jpastudy.mybook.domain.review.domain.Review;
import com.jpastudy.mybook.domain.review.dto.ReviewSearchDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value=
            "select r.review_id, m.member_id, b.book_id, r.content, r.score, b.title, b.isbn, b.image, b.author, r.created_date, r.modified_date"
            + " from review r"
            + " join member m on r.member_id = m.member_id "
            + " join book b on r.book_id = b.book_id "
            + " where m.member_id = :memberId", nativeQuery = true
    )
    Page<Review> findAllByMemberId(Long memberId, Pageable pageable);

    @Query(value=
            "select new com.jpastudy.mybook.domain.review.dto.ReviewSearchDetailDto(r.id, m.id, b.id, r.content, r.score, b.title, b.isbn, b.image, b.author, b.publisher, b.link, r.createdDate, r.modifiedDate)"
            + " from Review r"
            + " join r.member m"
            + " join r.book b"
            + " where r.id = :reviewId"
    )
    ReviewSearchDetailDto findByReviewIdWithBookInfo(Long reviewId);

}
