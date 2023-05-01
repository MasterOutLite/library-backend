package com.example.librarybackend.entity;

import com.example.librarybackend.dto.get.UserDto;
import com.example.librarybackend.entity.base.HistoryEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Users extends HistoryEntity {
//    name varchar(255) NOT NULL,
//    lastname varchar(255) NOT NULL,
//    phone varchar(255) NOT NULL,
//    mail varchar(255) DEFAULT 'NULL::character varying',
//    login varchar(30) NOT NULL,
//    password varchar(30) NOT NULL,

    @Column(nullable = false)
    private String uuid;

    @PrePersist
    private void onCreate() {
        uuid = UUID.randomUUID().toString();
    }

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String lastname;

    @Column(nullable = false)
    public String phone;

    @Column(nullable = true)
    public String mail;

    @Column(unique = true, nullable = false)
    public String login;

    @Column(nullable = false)
    public String password;


    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user")
    private List<Orders> order;
}
