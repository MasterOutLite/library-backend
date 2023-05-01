package com.example.librarybackend.entity;

import com.example.librarybackend.entity.base.HistoryEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Orders extends HistoryEntity {
//    id_book integer NOT NULL,
//    id_user integer NOT NULL,
//    id_librarian integer NOT NULL,
//    took_in_date date NOT NULL,
//    return_date date DEFAULT NULL,
//    is_return bool NOT NULL,

    @Column(nullable = false)
    public String uuid;

    @PrePersist
    private void onCreate(){
        uuid = UUID.randomUUID().toString();
        tookInDate = OffsetDateTime.now();
    }
    @ManyToOne
    @JoinColumn(nullable = false)
    public Books book;

    @ManyToOne
    @JoinColumn(nullable = false)
    public Users user;

    @ManyToOne
    @JoinColumn(nullable = true)
    public Librarian librarian;

    @Column(nullable = false)
    public OffsetDateTime tookInDate;

    @Column(nullable = true)
    public OffsetDateTime returnDate;

    @Column(nullable = false)
    public boolean returned;


}
