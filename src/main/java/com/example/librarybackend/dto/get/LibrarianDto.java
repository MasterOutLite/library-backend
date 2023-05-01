package com.example.librarybackend.dto.get;


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
    public String libraryUuid;
    public boolean working;
}
