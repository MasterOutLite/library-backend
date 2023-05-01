package com.example.librarybackend.controller;


import com.example.librarybackend.dto.get.UserDto;
import com.example.librarybackend.dto.post.NewUserDto;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;
import com.example.librarybackend.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UsersService usersService;

    @PostMapping("add")
    public void addUser(@RequestBody @Validated NewUserDto userDto) throws RecordExistsException {
        usersService.saveUser(userDto);
    }

    @GetMapping("{uuid}")
    public UserDto getUserByUuid(@PathVariable String uuid) throws NotFoundException {
        return usersService.getUserByUuid(uuid);
    }

    @GetMapping("all")
    public Iterable<UserDto> getAllUser() {
        return usersService.getAllUserDto();
    }

    @PutMapping
    public void putUser(@RequestBody UserDto userDto) throws NotFoundException {
        usersService.updateUser(userDto);
    }

    @DeleteMapping("{uuid}")
    public void deleteUserByUuid(@PathVariable String uuid) throws NotFoundException {
        usersService.deleteUserByUuid(uuid);
    }

    //----------------------------
    @GetMapping
    public NewUserDto getTemplateNewBookDto() {
        return new NewUserDto();
    }

}
