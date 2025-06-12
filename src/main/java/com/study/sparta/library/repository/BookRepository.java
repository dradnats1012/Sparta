package com.study.sparta.library.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.study.sparta.library.domain.Book;

public interface BookRepository extends Repository<Book, Integer> {

    List<Book> findAll();
}
