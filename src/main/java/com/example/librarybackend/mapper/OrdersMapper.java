package com.example.librarybackend.mapper;

import com.example.librarybackend.dto.get.OrdersDto;
import com.example.librarybackend.dto.post.NewOrdersDto;
import com.example.librarybackend.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    @Mapping(target = "bookUuid", source = "book.uuid")
    @Mapping(target = "userUuid", source = "user.uuid")
    @Mapping(target = "librarianUuid", source = "librarian.uuid")
    OrdersDto toOrdersDto(Orders orders);

    Iterable<OrdersDto> toIterableOrdersDto(Iterable<Orders> orders);

    Orders toEntity(NewOrdersDto ordersDto);
}
