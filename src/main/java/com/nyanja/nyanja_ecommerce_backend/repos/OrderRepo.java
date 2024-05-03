package com.nyanja.nyanja_ecommerce_backend.repos;

import com.nyanja.nyanja_ecommerce_backend.model.Orders;
import com.nyanja.nyanja_ecommerce_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Long> {
    List<Orders> findOrdersByCustomer(User customer);
}
