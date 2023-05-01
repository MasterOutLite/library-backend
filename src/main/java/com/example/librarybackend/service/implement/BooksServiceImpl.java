package com.example.librarybackend.service.implement;

import com.example.librarybackend.dto.get.BookDto;
import com.example.librarybackend.dto.post.NewBookDto;
import com.example.librarybackend.entity.Books;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.mapper.BookMapper;
import com.example.librarybackend.repository.BooksRepository;
import com.example.librarybackend.repository.LibraryRepository;
import com.example.librarybackend.service.BooksService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;
    private final BookMapper bookMapper;
    private final LibraryRepository libraryRepository;

    @Override
    public BookDto getBookByUuid(String uuid) throws NotFoundException {
        var book = booksRepository.findBooksByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        return bookMapper.toBookDto(book);
    }

    @Override
    public Iterable<BookDto> getAllBookDto() throws NotFoundException {
        var listBook = booksRepository.findAllByInLibrary(true)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        return bookMapper.toBookDtoIterable(listBook);
    }



    @Override
    public Iterable<BookDto> getAllBookDto(Boolean bookInLibrary) throws NotFoundException {
        var listBooks = booksRepository.findAllByInLibrary(bookInLibrary)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        return bookMapper.toBookDtoIterable(listBooks);
    }

    @Override
    public Iterable<BookDto> getAllBookDtoByName(String name, Boolean bookInLibrary) throws NotFoundException {
        var bookList = booksRepository.findAllByNameAndInLibrary(name, bookInLibrary)
                .orElseThrow(() -> new NotFoundException("Book not found by name: " + name));
        return bookMapper.toBookDtoIterable(bookList);
    }

    @Override
    public Iterable<BookDto> getAllBookDtoByGenre(String genre, Boolean bookInLibrary) throws NotFoundException {
        var bookList = booksRepository.findAllByGenreAndInLibrary(genre, bookInLibrary)
                .orElseThrow(() -> new NotFoundException("Book not found by genre: " + genre));
        return bookMapper.toBookDtoIterable(bookList);
    }

    @Override
    public Iterable<BookDto> getAllBookDto(Long page, Long amount) {
        Long end = page * amount;
        Long start = end - amount;

        var list = booksRepository.findAllByIdBetween(start, end)
                .orElseThrow(() -> new org.webjars.NotFoundException("Not found any books"));

        return bookMapper.toBookDtoIterable(list);
    }
    @Override
    public Iterable<BookDto> getAllBookDtoByFilter(String name, String genre, Boolean bookInLibrary, Long page, Long amount) throws NotFoundException {

        var bookList =
                booksRepository.findAllByNameLikeAndGenreLikeAndInLibrary
                                ("%" + name + "%", "%" + genre + "%", bookInLibrary)
                        .orElseThrow(() -> new NotFoundException("Book not found"));

        if (page <= 0 && amount <= 0)
            return bookMapper.toBookDtoIterable(bookList);

        Long end = page * amount;
        Long start = end - amount;

        if (start < bookList.size()) {
            int count = end < bookList.size() ? end.intValue() : bookList.size();
            bookList = bookList.subList(start.intValue(), count);
        } else
            bookList = null;

        return bookMapper.toBookDtoIterable(bookList);
    }

    @Override
    @Transactional
    public void updateBook(BookDto bookDto) throws NotFoundException {
        var book = booksRepository.findBooksByUuid(bookDto.uuid)
                .orElseThrow(() -> new NotFoundException("Not found book"));

        var library = libraryRepository.findLibraryByUuid(bookDto.libraryUuid)
                .orElseThrow(() -> new NotFoundException("Not found Library in book"));

        book.setLibrary(library);
        updateBook(bookDto, book);
    }

    private void updateBook(BookDto bookDto, Books book) {
        if (bookDto.getName() != null && !bookDto.getName().isEmpty())
            book.setName(bookDto.getName());
        if (bookDto.getGenre() != null && !bookDto.getGenre().isEmpty())
            book.setGenre(bookDto.getGenre());
//        Optional.ofNullable(bookDto.getName()).ifPresent(book::setName);
//        Optional.ofNullable(bookDto.getGenre()).ifPresent(book::setGenre);
        Optional.ofNullable(bookDto.getInLibrary()).ifPresent(book::setInLibrary);
    }

    @Override
    public void saveBook(NewBookDto bookDto) throws NotFoundException {
        var book = bookMapper.toEntity(bookDto);
        System.out.println("Debug Book: " + bookDto.name + " : " + bookDto.author + " : " + bookDto.libraryUuid);
        var library = libraryRepository.findLibraryByUuid(bookDto.libraryUuid)
                .orElseThrow(() -> new NotFoundException("Save book. Not found library"));

        book.setLibrary(library);
        booksRepository.save(book);
    }

    @Override
    public void deleteBookByUuid(String uuid) throws NotFoundException {
        var book = booksRepository.findBooksByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Not found book by uuid for delete: " + uuid));
        booksRepository.deleteByUuid(uuid);
    }
}
