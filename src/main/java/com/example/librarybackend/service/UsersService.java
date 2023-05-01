package com.example.librarybackend.service;

import com.example.librarybackend.dto.get.UserDto;
import com.example.librarybackend.dto.post.NewUserDto;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;

public interface UsersService {

    UserDto getUserByUuid(String uuid) throws NotFoundException;

    Iterable<UserDto> getAllUserDto();

    void updateUser(UserDto userDto) throws NotFoundException;

    void saveUser(NewUserDto user) throws RecordExistsException;

    void deleteUserByUuid(String uuid) throws NotFoundException;

}
