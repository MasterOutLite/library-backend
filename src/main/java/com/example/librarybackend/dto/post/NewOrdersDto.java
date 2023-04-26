package com.example.librarybackend.dto.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewOrdersDto {

    @NonNull
    public String book_uuid;
    @NonNull
    public String user_uuid;
    @NonNull
    public String librarian_uuid;
    @NonNull
    public OffsetDateTime tookInDate;
}
