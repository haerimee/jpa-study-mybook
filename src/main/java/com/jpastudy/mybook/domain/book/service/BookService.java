package com.jpastudy.mybook.domain.book.service;

import com.jpastudy.mybook.domain.book.domain.Book;
import com.jpastudy.mybook.domain.book.dto.BookDto;
import com.jpastudy.mybook.domain.book.dto.BookSearchDetailDto;
import com.jpastudy.mybook.domain.book.dto.BookSearchDto;
import com.jpastudy.mybook.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    private final String SEARCH_URL = "https://openapi.naver.com/v1/search/book.json?display=10";
    private final String DETAIL_URL = "https://openapi.naver.com/v1/search/book_adv.json";

    // 책 검색
    public BookSearchDto getBookSearchList(String keyword, int start){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntitiy();
        URI targetUrl = UriComponentsBuilder
                .fromUriString(SEARCH_URL)
                .queryParam("query", keyword)
                .queryParam("start", start)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        return restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, BookSearchDto.class).getBody();

    }

    // 책 상세 검색
    public BookSearchDetailDto getBookSearchDetail(String isbn){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntitiy();
        URI targetUrl = UriComponentsBuilder
                .fromUriString(DETAIL_URL)
                .queryParam("d_isbn", isbn)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        return restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, BookSearchDetailDto.class).getBody();
    }

    private HttpEntity<String> getHttpEntitiy() {
        // 헤더 인증 정보 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", "Cr52dCZN_yXZwLHwmpfF");
        httpHeaders.set("X-Naver-Client-Secret", "USgVQdI5Mz");
        return new HttpEntity<>(httpHeaders);
    }

    // 책 등록
    public Long saveBook(BookDto bookDto){
        Book newBook = bookDto.toEntity();

        bookRepository.save(newBook);
        return newBook.getId();
    }

}
