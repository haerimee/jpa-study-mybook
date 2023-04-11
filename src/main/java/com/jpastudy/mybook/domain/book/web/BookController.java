package com.jpastudy.mybook.domain.book.web;

import com.jpastudy.mybook.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<?> getBookSearchList(@RequestParam String keyword, @RequestParam int start){
        return new ResponseEntity<>(bookService.getBookSearchList(keyword, start), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getBookSearchDetail(@RequestParam String isbn){
        return new ResponseEntity<>(bookService.getBookSearchDetail(isbn), HttpStatus.OK);
    }

}
