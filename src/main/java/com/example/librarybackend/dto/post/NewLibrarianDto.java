package com.example.librarybackend.dto.add;

import com.example.librarybackend.entity.Library;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewLibrarianDto {

    @NonNull
    public String name;
    @NonNull
    public String lastname;
    @NonNull
    public String position;
    @NonNull
    public String library_uuid;
    @NonNull
    public String login;
    @NonNull
    public String password;
    @NonNull
    public Boolean working;
}
