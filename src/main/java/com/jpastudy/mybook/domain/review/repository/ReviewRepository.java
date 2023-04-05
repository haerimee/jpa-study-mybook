package com.jpastudy.mybook.domain.review.repository;

import com.jpastudy.mybook.domain.review.domain.Review;
import com.jpastudy.mybook.domain.review.dto.ReviewDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
        String jpql = "select r From Review r join r.member m where m.id = :member_id";
        Query query = em.createQuery(jpql);
        query.setParameter("member_id", memberId);

        return query.getResultList();
    }

}
