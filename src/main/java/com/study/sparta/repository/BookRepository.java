package com.study.sparta.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.study.sparta.domain.Book;

public interface BookRepository extends Repository<Book, Integer> {

    List<Book> findAll();
}
