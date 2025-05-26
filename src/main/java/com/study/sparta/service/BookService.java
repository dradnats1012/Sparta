package com.study.sparta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.sparta.domain.Book;
import com.study.sparta.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
