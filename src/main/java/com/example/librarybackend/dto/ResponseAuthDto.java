package com.example.librarybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseAuthDto {
    public String token;
    public String uuid;
    public Boolean isSuccess = false;
}
