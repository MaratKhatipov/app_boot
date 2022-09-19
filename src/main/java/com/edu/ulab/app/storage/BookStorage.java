package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;

import java.util.Collection;
import java.util.List;

public interface BookStorage  {
    Book create(Book book);

    Collection<Book> findAll();

    Book findById(Long id);

    Book update(Book book);

    void deleteById(Long id);

    List<Book> findBooksByUserId(Long id);
}
