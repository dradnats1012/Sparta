package com.study.sparta.schoolbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.sparta.schoolbook.domain.Book;
import com.study.sparta.schoolbook.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
