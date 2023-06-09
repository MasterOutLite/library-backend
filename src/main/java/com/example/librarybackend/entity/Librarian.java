package com.example.librarybackend.entity;

import com.example.librarybackend.entity.base.HistoryEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Librarian extends HistoryEntity {    @Column(nullable = false)
    private String uuid;

    @PrePersist
    private void onCreate(){
        uuid = UUID.randomUUID().toString();
    }


    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String lastname;

    @Column(nullable = false)
    public String position;

    @ManyToOne
    @JoinColumn(nullable = false)
    public Library library;

    @Column(unique=true,nullable = false)
    public String login;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public boolean working;


    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "librarian")
    private List<Orders> orders;
}
