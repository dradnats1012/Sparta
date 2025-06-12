package com.study.sparta.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.sparta.library.domain.Book;
import com.study.sparta.library.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
