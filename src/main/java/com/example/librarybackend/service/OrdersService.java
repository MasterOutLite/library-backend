package com.example.librarybackend.service;

import com.example.librarybackend.dto.get.OrdersDto;
import com.example.librarybackend.dto.post.NewOrdersDto;
import com.example.librarybackend.exception.NotFoundException;

public interface OrdersService {

    OrdersDto getOrdersByUuid(String uuid) throws NotFoundException;

    Iterable<OrdersDto> getAllOrdersDto();

    Iterable<OrdersDto> getAllOrdersDto(Boolean bookReturned) throws NotFoundException;

    void updateOrders(OrdersDto ordersDto) throws NotFoundException;

    void saveOrders(NewOrdersDto ordersDto) throws NotFoundException;

    void deleteOrdersByUuid(String uuid) throws NotFoundException;
}
