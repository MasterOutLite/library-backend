package com.example.librarybackend.controller;


import com.example.librarybackend.dto.get.BookDto;
import com.example.librarybackend.dto.post.NewBookDto;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("book")
@AllArgsConstructor
public class BookController {

    private final BooksService booksService;

    @PostMapping("add")
    public void addBook(@RequestBody @Validated NewBookDto book) throws NotFoundException {
        booksService.saveBook(book);
    }

    @GetMapping("{uuid}")
    public BookDto getBookByUuid(@PathVariable String uuid) throws NotFoundException {
        return booksService.getBookByUuid(uuid);
    }

    @GetMapping("all")
    public Iterable<BookDto> getBookByFilter
            (@RequestParam(required = false) Optional<String> name,
             @RequestParam(required = false) Optional<String> genre,
             @RequestParam(required = false) Optional<Long> page,
             @RequestParam(required = false) Optional<Long> amount,
             @RequestParam(required = false) Optional<Boolean> inLibrary) throws NotFoundException {

        return booksService.getAllBookDtoByFilter(name.orElse(""), genre.orElse(""),
                inLibrary.orElse(true),page.orElse(-1L),amount.orElse(-1L));
    }

    @PutMapping
    public void putBook(@RequestBody BookDto bookDto) throws NotFoundException {
        booksService.updateBook(bookDto);
    }

    @DeleteMapping("{uuid}")
    public void deleteBookByUuid(@PathVariable String uuid) throws NotFoundException {
        booksService.deleteBookByUuid(uuid);
    }

    //----------------------------
    @GetMapping
    public NewBookDto getTemplateNewBookDto() {
        return new NewBookDto();
    }

}
