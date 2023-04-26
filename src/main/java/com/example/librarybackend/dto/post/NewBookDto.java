package com.example.librarybackend.dto.add;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewBookDto {

    @NonNull
    public String name;
    @NonNull
    public String author;
    @NonNull
    public Boolean inLibrary;
    @NonNull
    public String library_uuid;
    @NonNull
    public String genre;
}
