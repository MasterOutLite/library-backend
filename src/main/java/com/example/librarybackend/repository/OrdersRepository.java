package com.example.librarybackend.repository;

import com.example.librarybackend.entity.Books;
import com.example.librarybackend.entity.Orders;
import com.example.librarybackend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    Optional<Orders> findOrdersById(Long id);
    Optional<Orders> findOrdersByUuid(String uuid);
    Optional<Orders> findOrdersByBookAndReturned(Books books, Boolean isReturn);
    Optional<Orders> findOrdersByUser(Users user);
    Optional<Orders> findOrdersByTookInDate(Date date);

    Optional<Iterable<Orders>> findAllByReturned(Boolean returned);
    Optional<Iterable<Orders>> findAllByTookInDateAndReturned(Date date,Boolean returned);

    void deleteByUuid(String uuid);

}
