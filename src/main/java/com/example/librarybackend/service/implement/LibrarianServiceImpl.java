package com.example.librarybackend.service.implement;

import com.example.librarybackend.dto.RequestAuthDto;
import com.example.librarybackend.dto.ResponseAuthDto;
import com.example.librarybackend.dto.get.LibrarianDto;
import com.example.librarybackend.dto.post.NewLibrarianDto;
import com.example.librarybackend.dto.put.PutLibrarianDto;
import com.example.librarybackend.entity.Librarian;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;
import com.example.librarybackend.mapper.LibrarianMapper;
import com.example.librarybackend.repository.LibrarianRepository;
import com.example.librarybackend.repository.LibraryRepository;
import com.example.librarybackend.service.LibrarianService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {

    private final LibrarianRepository librarianRepository;
    private final LibraryRepository libraryRepository;
    private final LibrarianMapper librarianMapper;

    @Override
    public LibrarianDto getLibrarianDtoByUuid(String uuid) throws NotFoundException {
        var librarian = librarianRepository.findLibrarianByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Not found Librarian by uuid: " + uuid));
        return librarianMapper.toLibrarianDto(librarian);
    }

    @Override
    public ResponseAuthDto logInLibrarian(RequestAuthDto authDto) {
        var librarian = librarianRepository.findLibrarianByLoginAndPassword(authDto.getLogin(), authDto.getPassword());
        var response = new ResponseAuthDto();
        System.out.println("Login: " +authDto.getLogin() + " : Password " + authDto.getPassword());
        response.token = "Login: " +authDto.getLogin() + " : Password " + authDto.getPassword();
        if(librarian.isPresent()){
            response.isSuccess = true;
            response.uuid = librarian.get().getUuid();
        }

        return response;
    }

    @Override
    public Iterable<LibrarianDto> getAllLibrarianDto() {
        var librarians = librarianRepository.findAll();
        return librarianMapper.toIterableLibrarianDto(librarians);
    }

    @Override
    public Iterable<LibrarianDto> getAllLibrarianDtoWorking(Boolean working) throws NotFoundException {
        var librarians = librarianRepository.findAllByWorking(working)
                .orElseThrow(() -> new NotFoundException("Not found librarian working: " + working));
        return librarianMapper.toIterableLibrarianDto(librarians);
    }

    @Override
    @Transactional
    public void updateLibrarian(PutLibrarianDto librarianDto) throws NotFoundException {
        Librarian librarian = librarianRepository.findLibrarianByUuid(librarianDto.uuid)
                .orElseThrow(() -> new NotFoundException("Not found user by uuid: " + librarianDto.uuid));

        var library = libraryRepository.findLibraryByUuid(librarianDto.uuid)
                .orElseThrow(() -> new NotFoundException("Not found Library in update Librarian"));

        librarian.setLibrary(library);
        updateLibrarian(librarianDto, librarian);
    }

    private void updateLibrarian(PutLibrarianDto librarianDto, Librarian librarian) {
        if (librarianDto.getName() != null && !librarianDto.getName().isEmpty())
            librarian.setName(librarianDto.getName());
        if (librarianDto.getLastname() != null && !librarianDto.getLastname().isEmpty())
            librarian.setLastname(librarianDto.getLastname());
        if (librarianDto.getPosition() != null && !librarianDto.getPosition().isEmpty())
            librarian.setPosition(librarianDto.getPosition());

        Optional.ofNullable(librarianDto.getWorking()).ifPresent(librarian::setWorking);

        if (librarianDto.getLogin() != null && !librarianDto.getLogin().isEmpty())
            librarian.setLogin(librarianDto.getLogin());
        if (librarianDto.getPassword() != null && !librarianDto.getPassword().isEmpty())
            librarian.setPassword(librarianDto.getPassword());
    }

    @Override
    public void saveLibrarian(NewLibrarianDto librarianDto) throws NotFoundException, RecordExistsException {
        var userExist = librarianRepository.findLibrarianByLogin(librarianDto.login).isPresent();
        if (userExist) {
            throw new RecordExistsException("Librarian exists with login: " + librarianDto.login);
        }

        var librarian = librarianMapper.toEntity(librarianDto);
        var library = libraryRepository.findLibraryByUuid(librarianDto.libraryUuid)
                .orElseThrow(() -> new NotFoundException("Save librarian. Not found Library for save"));

        librarian.setLibrary(library);
        librarianRepository.save(librarian);
    }

    @Override
    public void deleteLibrarianByUuid(String uuid) throws NotFoundException {
        var librarian = librarianRepository.findLibrarianByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Not found librarian by uuid for delete: " + uuid));
        librarian.working = false;
        librarianRepository.save(librarian);
    }
}
