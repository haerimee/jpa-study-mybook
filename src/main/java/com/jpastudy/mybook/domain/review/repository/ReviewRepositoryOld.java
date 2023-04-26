package com.jpastudy.mybook.domain.review.repository;

import com.jpastudy.mybook.domain.review.domain.Review;
import com.jpastudy.mybook.domain.review.dto.ReviewSearchDetailDto;
import com.jpastudy.mybook.domain.review.dto.ReviewSearchDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryOld {

    @PersistenceContext
    private EntityManager em;

    public void save(Review review){
        em.persist(review);
    }

    public Review findById(Long id){
        return em.find(Review.class, id);
    }

    public void delete(Review review){
        em.remove(review);
    }

    public ReviewSearchDetailDto findByReviewIdWithBookInfo(Long reviewId){
        String jpql =  "select new com.jpastudy.mybook.domain.review.dto.ReviewSearchDetailDto(r.id, m.id, b.id, r.content, r.score, b.title, b.isbn, b.image, b.author, b.publisher, b.link, r.createdDate, r.modifiedDate)"
                + " from Review r"
                + " join r.member m"
                + " join r.book b"
                + " where r.id = :review_id";
        Query query = em.createQuery(jpql, ReviewSearchDetailDto.class);
        query.setParameter("review_id", reviewId);

        return (ReviewSearchDetailDto) query.getSingleResult();
    }

    public List<ReviewSearchDto> findAllReviewsByMemberId(Long memberId){
        String jpql =  "select new com.jpastudy.mybook.domain.review.dto.ReviewSearchDto(r.id, m.id, b.id, r.content, r.score, b.title, b.isbn, b.image, b.author, r.createdDate, r.modifiedDate)"
                + " from Review r"
                + " join r.member m"
                + " join r.book b"
                + " where m.id = :member_id";
        Query query = em.createQuery(jpql, ReviewSearchDto.class);
        query.setParameter("member_id", memberId);

        return query.getResultList();
    }

}
