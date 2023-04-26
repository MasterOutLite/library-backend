package com.example.librarybackend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Setter(AccessLevel.NONE)
    public String uuid;

    public String name;
    public String lastname;
    public String phone;
    public String mail;
}
