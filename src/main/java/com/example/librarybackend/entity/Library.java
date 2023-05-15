package com.example.librarybackend.entity;

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
public class Library extends HistoryEntity {
    @Column(nullable = false)
    private String uuid;

    @PrePersist
    private void onCreate(){
        uuid = UUID.randomUUID().toString();
    }

    @Column(nullable = false)
    public String address;

    @Column(nullable = false)
    public String name;


    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "library")
    private List<Books> books;
}
