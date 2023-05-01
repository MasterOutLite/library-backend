package com.example.librarybackend.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidationException extends Exception {
    public String msg;
    public Integer code;

    public ValidationException(String msg){
        this.code = 40;
        this.msg = msg;
    }
}
