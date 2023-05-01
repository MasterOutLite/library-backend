package com.example.librarybackend.mapper;

import com.example.librarybackend.dto.get.UserDto;
import com.example.librarybackend.dto.post.NewUserDto;
import com.example.librarybackend.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "name", source = "name")
    Users toEntity(NewUserDto userDto);
    UserDto toUserDto(Users user);

    Iterable<UserDto> toIterableUser(Iterable<Users> users);
}
