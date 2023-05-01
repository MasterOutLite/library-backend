package com.example.librarybackend.mapper;

import com.example.librarybackend.dto.get.BookDto;
import com.example.librarybackend.dto.post.NewBookDto;
import com.example.librarybackend.entity.Books;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "libraryUuid", source = "library.uuid")
    BookDto toBookDto(Books book);

    Books toEntity(NewBookDto bookDto);

    Iterable<BookDto> toBookDtoIterable(Iterable<Books> listBooks);
}
