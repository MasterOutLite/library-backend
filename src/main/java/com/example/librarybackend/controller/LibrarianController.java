package com.example.librarybackend.controller;


import com.example.librarybackend.dto.RequestAuthDto;
import com.example.librarybackend.dto.ResponseAuthDto;
import com.example.librarybackend.dto.get.LibrarianDto;
import com.example.librarybackend.dto.post.NewLibrarianDto;
import com.example.librarybackend.dto.put.PutLibrarianDto;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;
import com.example.librarybackend.service.LibrarianService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("librarian")
@AllArgsConstructor
public class LibrarianController {

    private final LibrarianService librarianService;

    @PostMapping("add")
    public void addLibrarian(@RequestBody @Validated NewLibrarianDto librarianDto) throws NotFoundException, RecordExistsException {
        librarianService.saveLibrarian(librarianDto);
    }

    @GetMapping("{uuid}")
    public LibrarianDto getLibrarianDtoByUuid(@PathVariable String uuid) throws NotFoundException {
        return librarianService.getLibrarianDtoByUuid(uuid);
    }

    @PostMapping("login")
    public ResponseAuthDto logInLibrarian(@RequestBody RequestAuthDto authDto){
        System.out.println(authDto.getLogin() +" " + authDto.getPassword() + " " + authDto.getToken());
        return librarianService.logInLibrarian(authDto);
    }

    @GetMapping("loginT")
    public RequestAuthDto getLogInLibrarian(){
        return new RequestAuthDto("token","login","password");
    }


    @GetMapping("all")
    public Iterable<LibrarianDto> getLibrarianDtoByUuid() {
        return librarianService.getAllLibrarianDto();
    }

    @GetMapping("all/{working}")
    public Iterable<LibrarianDto> getLibrarianDtoByUuid(@PathVariable Boolean working) throws NotFoundException {
        return librarianService.getAllLibrarianDtoWorking(working);
    }

    @PutMapping
    public void putLibrarian(@RequestBody PutLibrarianDto librarianDto) throws NotFoundException {
        librarianService.updateLibrarian(librarianDto);
    }

    @DeleteMapping("{uuid}")
    public void deleteLibrarianByUuid(@PathVariable String uuid) throws NotFoundException {
        librarianService.deleteLibrarianByUuid(uuid);
    }

    //----------------------------
    @GetMapping
    public NewLibrarianDto getTemplateNewBookDto() {
        return new NewLibrarianDto();
    }

    @GetMapping("put")
    public PutLibrarianDto getTemplatePutLibrarianDto() {
        return new PutLibrarianDto();
    }

}
