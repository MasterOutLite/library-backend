package com.example.librarybackend.dto.get;

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
    public String libraryUuid;
    public String genre;
    public String author;
}
