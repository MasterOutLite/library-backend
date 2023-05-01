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
public class NewUserDto {

    @NonNull
    public String name;

    @NonNull
    public String lastname;

    @NonNull
    public String phone;

    public String mail;

    @NonNull
    public String login;

    @NonNull
    public String password;
}
