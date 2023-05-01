package com.example.librarybackend.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecordExistsException extends Exception {
    public String msg;
    public Integer code;

    public RecordExistsException(String msg){
        this.code = 30;
        this.msg = msg;
    }
}
