package com.study.sparta.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.sparta.library.domain.Book;
import com.study.sparta.library.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }
}
