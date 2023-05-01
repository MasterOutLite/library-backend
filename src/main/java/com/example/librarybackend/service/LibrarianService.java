package com.example.librarybackend.service;

import com.example.librarybackend.dto.RequestAuthDto;
import com.example.librarybackend.dto.ResponseAuthDto;
import com.example.librarybackend.dto.get.LibrarianDto;
import com.example.librarybackend.dto.post.NewLibrarianDto;
import com.example.librarybackend.dto.put.PutLibrarianDto;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;

public interface LibrarianService {

    LibrarianDto getLibrarianDtoByUuid(String uuid) throws NotFoundException;
    ResponseAuthDto logInLibrarian(RequestAuthDto authDto);
    Iterable<LibrarianDto> getAllLibrarianDto();

    Iterable<LibrarianDto> getAllLibrarianDtoWorking(Boolean working) throws NotFoundException;

    void updateLibrarian(PutLibrarianDto librarianDto) throws NotFoundException;

    void saveLibrarian(NewLibrarianDto librarianDto) throws NotFoundException, RecordExistsException;

    void deleteLibrarianByUuid(String uuid) throws NotFoundException;
}
