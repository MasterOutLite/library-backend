package com.example.librarybackend.mapper;

import com.example.librarybackend.dto.get.ExceptionMsgDto;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.exception.RecordExistsException;
import com.example.librarybackend.exception.ValidationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.dao.DataIntegrityViolationException;

@Mapper(componentModel = "spring")
public interface ExceptionMapper {

    ExceptionMsgDto toDto(NotFoundException ex);

    ExceptionMsgDto toDto(RecordExistsException ex);

    ExceptionMsgDto toDto(ValidationException ex);

}
