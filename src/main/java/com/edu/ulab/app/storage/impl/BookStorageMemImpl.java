package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.storage.BookStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BookStorageMemImpl implements BookStorage {

    private final AtomicLong currentId = new AtomicLong(0);
    private final ConcurrentHashMap<Long, Book> booksStorage = new ConcurrentHashMap<>();

    @Override
    public Book create(Book book) {
        book.setId(currentId.incrementAndGet());
        booksStorage.put(currentId.get(), book);
        return book;
    }

    @Override
    public Collection<Book> findAll() {
        return booksStorage.values();
    }

    @Override
    public Book findById(Long id) {
        return booksStorage.get(id);
    }

    @Override
    public Book update(Book book) {
        if (!booksStorage.containsKey(book.getId())) {
            create(book);
        } else {
            Book updateBook = booksStorage.get(book.getId());
            updateBook.setTitle(book.getTitle());
            updateBook.setAuthor(book.getAuthor());
            updateBook.setPageCount(book.getPageCount());
            log.info("book update: {}", book);
        }
        booksStorage.replace(book.getId(), book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        booksStorage.remove(id);
    }

    @Override
    public List<Book> findBooksByUserId(Long id) {
        return booksStorage.values()
                .stream()
                .filter(book -> id.equals(book.getUserId()))
                .collect(Collectors.toList());
    }
}
