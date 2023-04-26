package com.example.librarybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {

    private String uuid;
    public String book_uuid;
    public String user_uuid;
    public String librarian_uuid;
    public OffsetDateTime tookInDate;
    public OffsetDateTime returnDate;
    public boolean returned;
}
