package com.example.librarybackend.repository;

import com.example.librarybackend.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

    Optional<Books> findBooksById(Long id);

    Optional<Books> findBooksByUuid(String uuid);

    Optional<List<Books>> findAllByGenreAndInLibrary(String genre, Boolean inLibrary);

    Optional<List<Books>> findAllByNameAndInLibrary(String name, Boolean inLibrary);

    Optional<List<Books>> findAllByIdBetween(Long start, Long end);

    Optional<List<Books>> findAllByAuthorAndInLibrary(String author, Boolean inLibrary);

    Optional<List<Books>> findAllByNameAndGenreAndInLibrary(String name, String genre, Boolean inLibrary);

    Optional<List<Books>> findAllByNameLikeAndGenreLikeAndInLibrary(String name, String genre, Boolean inLibrary);

    Optional<List<Books>> findAllByGenreAndInLibraryTrue(String genre);

    Optional<List<Books>> findAllByInLibrary(Boolean inLibrary);

    void deleteByUuid(String uuid);
}
