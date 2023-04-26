package com.jpastudy.mybook.domain.book.repository;

import com.jpastudy.mybook.domain.book.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryOld {

    @PersistenceContext
    private EntityManager em;

    public Book save(Book book){
        em.persist(book);
        return book;
    }

    public  Book findByBookId(Long id){
        return em.find(Book.class, id);
    }
}
