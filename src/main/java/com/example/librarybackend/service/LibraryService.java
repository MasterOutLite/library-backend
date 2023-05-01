package com.example.librarybackend.service;

import com.example.librarybackend.dto.RequestAuthDto;
import com.example.librarybackend.dto.ResponseAuthDto;
import com.example.librarybackend.dto.get.LibraryDto;
import com.example.librarybackend.dto.post.NewLibraryDto;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;

public interface LibraryService {

    LibraryDto getLibraryByUuid(String uuid) throws NotFoundException;

    LibraryDto getLibraryByName(String name) throws NotFoundException;

    LibraryDto getLibraryByAddress(String Address) throws NotFoundException;

    Iterable<LibraryDto> getAllLibrary();

    void updateLibrary(LibraryDto libraryDto) throws NotFoundException;

    void saveLibrary(NewLibraryDto libraryDto) throws RecordExistsException;

    void deleteLibraryByUuid(String uuid) throws NotFoundException;

}
