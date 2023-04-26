package com.example.librarybackend.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibrarianDto {
    @Setter(AccessLevel.NONE)
    public String uuid;

    public String name;
    public String lastname;
    public String position;
    public String library_uuid;
    public boolean working;
}
