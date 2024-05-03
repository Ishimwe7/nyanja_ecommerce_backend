package com.nyanja.nyanja_ecommerce_backend.services;

import com.nyanja.nyanja_ecommerce_backend.model.Orders;
import com.nyanja.nyanja_ecommerce_backend.model.User;
import com.nyanja.nyanja_ecommerce_backend.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepository;
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
    public Orders getOrderById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Orders> getOrdersByCustomer(User customer) {
        return orderRepository.findOrdersByCustomer(customer);
    }
    public Orders createOrder(Orders orders) {
        return orderRepository.save(orders);
    }

    public void updateOrder(long id, Orders orders) {
        if (orderRepository.existsById(id)) {
            orders.setId(id);
            orderRepository.save(orders);
        }
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

}
