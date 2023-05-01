package com.example.librarybackend.repository;

import com.example.librarybackend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    Optional<Users> findUsersById(Long id);
    Optional<Users> findUsersByUuid(String uuid);
    Optional<Users> findUsersByLogin(String login);
    Optional<Users> findUsersByName(String name);
    void deleteUsersByUuid(String uuid);
}
