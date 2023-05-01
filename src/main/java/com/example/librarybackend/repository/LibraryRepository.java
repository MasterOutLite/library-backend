package com.example.librarybackend.repository;

import com.example.librarybackend.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    Optional<Library> findLibraryById(Long id);
    Optional<Library> findLibraryByUuid(String uuid);
    Optional<Library> findLibraryByName(String name);
    Optional<Library> findLibraryByAddress(String address);

    void deleteByUuid(String uuid);
}
