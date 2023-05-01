package com.example.librarybackend.controller;

import com.example.librarybackend.dto.get.OrdersDto;
import com.example.librarybackend.dto.post.NewOrdersDto;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("add")
    public void addOrders(@RequestBody @Validated NewOrdersDto ordersDto) throws NotFoundException {
        ordersService.saveOrders(ordersDto);
    }

    @GetMapping("{uuid}")
    public OrdersDto getOrdersByUuid(@PathVariable String uuid) throws NotFoundException {
        return ordersService.getOrdersByUuid(uuid);
    }

    @GetMapping("all")
    public Iterable<OrdersDto> getAllOrders() {
        return ordersService.getAllOrdersDto();
    }

    @PutMapping
    public void putOrders(@RequestBody OrdersDto ordersDto) throws NotFoundException {
        ordersService.updateOrders(ordersDto);
    }

    @DeleteMapping("{uuid}")
    public void deleteOrdersByUuid(@PathVariable String uuid) throws NotFoundException {
        ordersService.deleteOrdersByUuid(uuid);
    }

    //----------------------------
    @GetMapping
    public NewOrdersDto getTemplateNewBookDto() {
        return new NewOrdersDto();
    }

}
