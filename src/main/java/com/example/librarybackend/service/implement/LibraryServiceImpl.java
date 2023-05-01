package com.example.librarybackend.service.implement;

import com.example.librarybackend.dto.RequestAuthDto;
import com.example.librarybackend.dto.ResponseAuthDto;
import com.example.librarybackend.dto.get.LibraryDto;
import com.example.librarybackend.dto.post.NewLibraryDto;
import com.example.librarybackend.entity.Library;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;
import com.example.librarybackend.mapper.LibraryMapper;
import com.example.librarybackend.repository.LibraryRepository;
import com.example.librarybackend.service.LibraryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final LibraryMapper libraryMapper;

    @Override
    public LibraryDto getLibraryByUuid(String uuid) throws NotFoundException {
        var library = libraryRepository.findLibraryByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Library not found by uuid: " + uuid));
        return libraryMapper.toLibraryDto(library);
    }

    @Override
    public LibraryDto getLibraryByName(String name) throws NotFoundException {
        var library = libraryRepository.findLibraryByName(name)
                .orElseThrow(() -> new NotFoundException("Library not found by name: " + name));
        return libraryMapper.toLibraryDto(library);
    }

    @Override
    public LibraryDto getLibraryByAddress(String address) throws NotFoundException {
        var library = libraryRepository.findLibraryByAddress(address)
                .orElseThrow(() -> new NotFoundException("Library not found by address: " + address));
        return libraryMapper.toLibraryDto(library);
    }

    @Override
    public Iterable<LibraryDto> getAllLibrary() {
        var library = libraryRepository.findAll();
        return libraryMapper.toIterableLibraryDto(library);
    }

    @Override
    public void saveLibrary(NewLibraryDto libraryDto) throws RecordExistsException {
        var finLibrary = libraryRepository.findLibraryByName(libraryDto.name)
                .isPresent();
        if (finLibrary) {
            throw new RecordExistsException("Library exists with name: " + libraryDto.name);
        }

        var library = libraryMapper.toEntity(libraryDto);
        libraryRepository.save(library);
    }

    @Override
    @Transactional
    public void updateLibrary(LibraryDto libraryDto) throws NotFoundException {
        Library library = libraryRepository.findLibraryByUuid(libraryDto.uuid)
                .orElseThrow(() -> new NotFoundException("Not found user by uuid: " + libraryDto.uuid));

        updateLibrary(libraryDto, library);
    }

    private void updateLibrary(LibraryDto libraryDto, Library library) {
        if (libraryDto.getAddress() != null && !libraryDto.getAddress().isEmpty())
            library.setAddress(libraryDto.getAddress());
        if (libraryDto.getName() != null && !libraryDto.getName().isEmpty())
            library.setName(libraryDto.getName());
//        Optional.ofNullable(libraryDto.getAddress()).ifPresent(library::setAddress);
//        Optional.ofNullable(libraryDto.getName()).ifPresent(library::setName);
    }

    @Override
    public void deleteLibraryByUuid(String uuid) throws NotFoundException {
        var library = libraryRepository.findLibraryByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Not found library by uuid for delete: " + uuid));
        libraryRepository.deleteByUuid(uuid);
    }
}
