package com.example.librarybackend.mapper;

import com.example.librarybackend.dto.get.LibraryDto;
import com.example.librarybackend.dto.post.NewLibraryDto;
import com.example.librarybackend.entity.Library;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryMapper {

    Library toEntity(NewLibraryDto libraryDto);
    LibraryDto toLibraryDto(Library library);
    Iterable<LibraryDto> toIterableLibraryDto(Iterable<Library> libraries);
}
