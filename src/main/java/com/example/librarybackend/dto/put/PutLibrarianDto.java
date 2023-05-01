package com.example.librarybackend.dto.put;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutLibrarianDto {
    @Setter(AccessLevel.NONE)
    public String uuid;

    public String name;
    public String lastname;
    public String position;
    public String library_uuid;
    public Boolean working;

    public String login;
    public String password;
}
