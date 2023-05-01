package com.example.librarybackend.dto.get;

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
    public String bookUuid;
    public String userUuid;
    public String librarianUuid;
    public OffsetDateTime tookInDate;
    public OffsetDateTime returnDate;
    public Boolean returned;
}
