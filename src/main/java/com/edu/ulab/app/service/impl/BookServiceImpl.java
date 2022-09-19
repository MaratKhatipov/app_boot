package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.BookStorage;
import com.edu.ulab.app.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookStorage bookStorage;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookStorage bookStorage, BookMapper bookMapper) {
        this.bookStorage = bookStorage;
        this.bookMapper = bookMapper;
    }


    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);
        return bookMapper.bookToBookDto(bookStorage.create(book));
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book updateBook = bookMapper.bookDtoToBook(bookDto);
        return bookMapper.bookToBookDto(bookStorage.update(updateBook));
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookMapper.bookToBookDto(bookStorage.findById(id));
    }

    @Override
    public void deleteBookById(Long id) {
        bookStorage.deleteById(id);
    }

    @Override
    public List<BookDto> getAll() {
        return bookStorage.findAll()
                .stream()
                .map(bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBookByUserId(Long id) {
        return bookStorage.findBooksByUserId(id)
                .stream()
                .map(bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }
}
