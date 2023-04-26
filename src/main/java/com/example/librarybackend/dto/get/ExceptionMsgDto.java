package com.example.librarybackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionMsgDto {
    public String msg;
    public Integer code;

    public ExceptionMsgDto(){
        this.msg = "Exception";
        this.code = 0;
    }
}
