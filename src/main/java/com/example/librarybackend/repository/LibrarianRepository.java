package com.example.librarybackend.repository;

import com.example.librarybackend.entity.Librarian;
import com.example.librarybackend.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

    Optional<Librarian> findLibrarianById(Long id);
    Optional<Librarian> findLibrarianByUuid(String uuid);
    Optional<Librarian> findLibrarianByLoginAndPassword(String login,String password);
    Optional<Librarian> findLibrarianByLogin(String login);
    Optional<Librarian> findLibrarianByLibraryAndWorking(Library idLibrary, Boolean working);
    Optional<Iterable<Librarian>> findAllByWorking(Boolean working);

    void deleteByUuid(String uuid);
}
