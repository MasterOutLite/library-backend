package com.example.librarybackend.dto.post;

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
    public String libraryUuid;
    @NonNull
    public String login;
    @NonNull
    public String password;
    @NonNull
    public Boolean working;
}
