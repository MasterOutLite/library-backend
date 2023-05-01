package com.example.librarybackend.mapper;

import com.example.librarybackend.dto.get.LibrarianDto;
import com.example.librarybackend.dto.post.NewLibrarianDto;
import com.example.librarybackend.entity.Librarian;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LibrarianMapper {

    Librarian toEntity (NewLibrarianDto librarianDto);
    @Mapping(target = "libraryUuid", source = "library.uuid")
    LibrarianDto toLibrarianDto(Librarian librarian);

    Iterable<LibrarianDto> toIterableLibrarianDto(Iterable<Librarian> librarians);
}
