package com.example.librarybackend.entity;

import com.example.librarybackend.entity.base.HistoryEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Column;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Books extends HistoryEntity {
//    name varchar(255) NOT NULL,
//    in_library bool NOT NULL,
//    id_library integer NOT NULL,
//    genre varchar(30) NOT NULL,


    @Column(nullable = false)
    private String uuid;

    @PrePersist
    private void onCreate(){
        uuid = UUID.randomUUID().toString();
    }

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String author;

    @Column(nullable = false)
    public boolean inLibrary;

    @ManyToOne
    @JoinColumn(nullable = false)
    public Library library;

    @Column(nullable = false)
    public String genre;


    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "book")
    private List<Orders> orders;

}
