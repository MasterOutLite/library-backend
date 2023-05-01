package com.example.librarybackend.service.implement;

import com.example.librarybackend.dto.get.UserDto;
import com.example.librarybackend.dto.post.NewUserDto;
import com.example.librarybackend.entity.Users;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;
import com.example.librarybackend.mapper.UserMapper;
import com.example.librarybackend.repository.UsersRepository;
import com.example.librarybackend.service.UsersService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserByUuid(String uuid) throws NotFoundException {
        var user = usersRepository.findUsersByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Not found User by uuid: " + uuid));
        return userMapper.toUserDto(user);
    }

    @Override
    public Iterable<UserDto> getAllUserDto() {
        var users = usersRepository.findAll();
        return userMapper.toIterableUser(users);
    }

    @Override
    public void saveUser(NewUserDto userDto) throws RecordExistsException {
        var userExist = usersRepository.findUsersByLogin(userDto.login).isPresent();
        if (userExist) {
            throw new RecordExistsException("Users exists with login: " + userDto.login);
        }
        var user = userMapper.toEntity(userDto);
        usersRepository.save(user);
    }

    @Override
    public void deleteUserByUuid(String uuid) throws NotFoundException {
        var user = usersRepository.findUsersByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Not found user by uuid for delete: " + uuid));
        usersRepository.deleteUsersByUuid(uuid);
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto) throws NotFoundException {
        var user = usersRepository.findUsersByUuid(userDto.uuid)
                .orElseThrow(() -> new NotFoundException("Not found user by uuid: " + userDto.uuid));

        updateUsers( user ,userDto);
        //toUpdateUsers(user,userDto);
    }

//    private static void updateUser(UserDto userDto, Users user) {
//        Optional.ofNullable(userDto.getName()).ifPresent(user::setName);
//        Optional.ofNullable(userDto.getLastname()).ifPresent(user::setLastname);
//        Optional.ofNullable(userDto.getMail()).ifPresent(user::setMail);
//        Optional.ofNullable(userDto.getPhone()).ifPresent(user::setPhone);
//    }

    public void updateUsers(Users users, UserDto userDto) {
        if (users == null || userDto == null) {
            return;
        }

        users.name = userDto.name == null || userDto.name.isEmpty() ? users.name : userDto.name;
        users.lastname = userDto.lastname == null || userDto.lastname.isEmpty() ? users.lastname : userDto.lastname;
        users.phone = userDto.phone == null || userDto.phone.isEmpty() ? users.phone : userDto.phone;
        users.mail = userDto.mail == null || userDto.mail.isEmpty() ? users.mail : userDto.mail;
    }
}
