package com.example.librarybackend.mapper;

import com.example.librarybackend.dto.get.OrdersDto;
import com.example.librarybackend.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DtoMapper {
    @Mapping(target = "bookUuid", source = "orders.book.uuid")
    @Mapping(target = "userUuid", source = "orders.user.uuid")
    @Mapping(target = "librarianUuid", source = "orders.librarian.uuid")
    OrdersDto toOrdersDto(Orders orders);
}
