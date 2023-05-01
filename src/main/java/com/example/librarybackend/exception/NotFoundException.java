package com.example.librarybackend.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotFoundException extends Exception{

    public String msg;
    public Integer code;

    public NotFoundException(String msg){
        this.code = 23;
        this.msg = msg;
    }
}
