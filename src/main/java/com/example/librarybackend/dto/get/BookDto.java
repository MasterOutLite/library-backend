package com.example.librarybackend.dto;

import com.example.librarybackend.entity.Library;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    public String uuid;
    public String name;
    public Boolean inLibrary;
    public String library_uuid;
    public String genre;
}
