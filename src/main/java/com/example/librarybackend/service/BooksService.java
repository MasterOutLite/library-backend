package com.example.librarybackend.service;

import com.example.librarybackend.dto.get.BookDto;
import com.example.librarybackend.dto.post.NewBookDto;
import com.example.librarybackend.exception.NotFoundException;

import java.util.Optional;

public interface BooksService {

    BookDto getBookByUuid(String uuid) throws NotFoundException;

    Iterable<BookDto> getAllBookDto() throws NotFoundException;

    Iterable<BookDto> getAllBookDto(Long page,Long amount);
    Iterable<BookDto> getAllBookDto(Boolean bookInLibrary) throws NotFoundException;

    Iterable<BookDto> getAllBookDtoByName(String name, Boolean bookInLibrary) throws NotFoundException;

    Iterable<BookDto> getAllBookDtoByGenre(String genre, Boolean bookInLibrary) throws NotFoundException;
    Iterable<BookDto> getAllBookDtoByFilter(String name, String genre, Boolean bookInLibrary,Long page,Long amount) throws NotFoundException;
    void updateBook(BookDto bookDto) throws NotFoundException;

    void saveBook(NewBookDto bookDto) throws NotFoundException;

    void deleteBookByUuid(String uuid) throws NotFoundException;

}
