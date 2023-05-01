package com.example.librarybackend.service.implement;

import com.example.librarybackend.dto.get.OrdersDto;
import com.example.librarybackend.dto.post.NewOrdersDto;
import com.example.librarybackend.entity.Librarian;
import com.example.librarybackend.entity.Orders;
import com.example.librarybackend.exception.NotFoundException;
import com.example.librarybackend.mapper.OrdersMapper;
import com.example.librarybackend.repository.BooksRepository;
import com.example.librarybackend.repository.LibrarianRepository;
import com.example.librarybackend.repository.OrdersRepository;
import com.example.librarybackend.repository.UsersRepository;
import com.example.librarybackend.service.OrdersService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;

    private final UsersRepository usersRepository;
    private final LibrarianRepository librarianRepository;
    private final BooksRepository booksRepository;

    @Override
    public OrdersDto getOrdersByUuid(String uuid) throws NotFoundException {
        var orders = ordersRepository.findOrdersByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Not found Orders by uuid: " + uuid));
        return ordersMapper.toOrdersDto(orders);
    }

    @Override
    public Iterable<OrdersDto> getAllOrdersDto() {
        var ordersList = ordersRepository.findAll();
        return ordersMapper.toIterableOrdersDto(ordersList);
    }

    @Override
    public Iterable<OrdersDto> getAllOrdersDto(Boolean bookReturned) throws NotFoundException {
        var ordersList = ordersRepository.findAllByReturned(bookReturned)
                .orElseThrow(() -> new NotFoundException("Not found Orders how is not returned"));
        return ordersMapper.toIterableOrdersDto(ordersList);
    }

    @Override
    @Transactional
    public void updateOrders(OrdersDto ordersDto) throws NotFoundException {
        var orders = ordersRepository.findOrdersByUuid(ordersDto.getUuid())
                .orElseThrow(() -> new NotFoundException("Orders not found"));

        updateOrders(ordersDto,orders);
    }

    private void updateOrders(OrdersDto ordersDto, Orders orders) {
        Optional.ofNullable(ordersDto.getReturnDate()).ifPresent(orders::setReturnDate);
        Optional.ofNullable(ordersDto.getReturned()).ifPresent(orders::setReturned);
    }

    @Override
    public void saveOrders(NewOrdersDto ordersDto) throws NotFoundException {
        var orders = ordersMapper.toEntity(ordersDto);
        orders.returned = false;
        orders.tookInDate = OffsetDateTime.now();

        var book = booksRepository.findBooksByUuid(ordersDto.bookUuid)
                .orElseThrow(() -> new NotFoundException("Save orders. Not found book  " + ordersDto.bookUuid));
        var user = usersRepository.findUsersByUuid(ordersDto.userUuid)
                .orElseThrow(() -> new NotFoundException("Save orders. Not found user  " + ordersDto.userUuid) );

        Librarian librarian = null;
        if (!ordersDto.librarianUuid.isEmpty() || !ordersDto.librarianUuid.isBlank())
            librarian = librarianRepository.findLibrarianByUuid(ordersDto.librarianUuid)
                .orElseThrow(() -> new NotFoundException("Save orders. Not found librarian  " + ordersDto.librarianUuid));

        orders.setBook(book);
        orders.setUser(user);
        orders.setLibrarian(librarian);

        ordersRepository.save(orders);
    }

    @Override
    public void deleteOrdersByUuid(String uuid) throws NotFoundException {
        var orders = ordersRepository.findOrdersByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Not found orders by uuid for delete: " + uuid));
        ordersRepository.deleteByUuid(uuid);
    }
}
