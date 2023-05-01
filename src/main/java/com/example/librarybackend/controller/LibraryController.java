package com.example.librarybackend.controller;


import com.example.librarybackend.dto.get.LibraryDto;
import com.example.librarybackend.dto.post.NewLibraryDto;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;
import com.example.librarybackend.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("library")
@AllArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping("add")
    public void addLibrary(@RequestBody @Validated NewLibraryDto libraryDto) throws RecordExistsException {
        libraryService.saveLibrary(libraryDto);
    }

    @GetMapping("{uuid}")
    public LibraryDto getLibraryByUuid(@PathVariable String uuid) throws NotFoundException {
        return libraryService.getLibraryByUuid(uuid);
    }

    @GetMapping("all")
    public Iterable<LibraryDto> getAllLibrary() {
        return libraryService.getAllLibrary();
    }

    @PutMapping
    public void putLibrary(@RequestBody LibraryDto libraryDto) throws NotFoundException {
        libraryService.updateLibrary(libraryDto);
    }

    @DeleteMapping("{uuid}")
    public void deleteLibraryByUuid(@PathVariable String uuid) throws NotFoundException {
        libraryService.deleteLibraryByUuid(uuid);
    }

    //------------------------------
    @GetMapping
    public NewLibraryDto getTemplateNewBookDto() {
        return new NewLibraryDto();
    }

}
