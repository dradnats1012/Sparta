package com.study.sparta.schoolbook.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.study.sparta.schoolbook.domain.Book;

public interface BookRepository extends Repository<Book, Integer> {

    List<Book> findAll();
}
