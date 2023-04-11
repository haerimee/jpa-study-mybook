package com.jpastudy.mybook.domain.review.repository;

import com.jpastudy.mybook.domain.review.domain.Review;
import com.jpastudy.mybook.domain.review.dto.ReviewDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Review review){
        em.persist(review);
    }

    public Review findByReviewId(Long id){
        return em.find(Review.class, id);
    }

    public List<ReviewDto> findAllReviewsByMemberId(Long memberId){
      //String jpql =  "select new com.jpastudy.mybook.domain.review.dto.ReviewDto(r.id, m.id, b.id, r.content, r.score)"
        String jpql =  "select new com.jpastudy.mybook.domain.review.dto.ReviewSearchDto(r.id, m.id, b.id, r.content, r.score, b.title, b.isbn, b.image)"
                + " from Review r"
                + " join r.member m"
                + " join r.book b"
                + " where m.id = :member_id";
        Query query = em.createQuery(jpql, ReviewDto.class);
        query.setParameter("member_id", memberId);

        return query.getResultList();
    }

}
