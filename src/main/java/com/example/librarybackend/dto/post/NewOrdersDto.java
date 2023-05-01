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
public class NewOrdersDto {

    @NonNull
    public String bookUuid;
    @NonNull
    public String userUuid;
    @NonNull
    public String librarianUuid;
}
