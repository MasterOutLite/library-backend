package com.example.librarybackend.exception.handler;

import com.example.librarybackend.dto.get.ExceptionMsgDto;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;
import com.example.librarybackend.exception.ValidationException;
import com.example.librarybackend.mapper.ExceptionMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@AllArgsConstructor
public class ExceptionController {

    private final ExceptionMapper mapper;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMsgDto notFoundException(NotFoundException ex) {
        return mapper.toDto(ex);
    }

    @ExceptionHandler(RecordExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMsgDto RecordExistsException(RecordExistsException ex) {
        return mapper.toDto(ex);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMsgDto ValidationException(ValidationException ex) {
        System.out.println(ex);
        return mapper.toDto(ex);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMsgDto validationException(DataIntegrityViolationException ex) {
        return new ExceptionMsgDto("The data is incorrect or some fields are missing", 50);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMsgDto notFoundMethodRequest(HttpRequestMethodNotSupportedException ex){
        return new ExceptionMsgDto("Not found method request. " + ex,60);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMsgDto notImpl(Exception ex) {
        return new ExceptionMsgDto(ex.getMessage() + ". Class: " + ex.getClass(), 111);
    }
}
