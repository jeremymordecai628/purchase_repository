package com.modex.webapp.service;

import com.modex.webapp.model.Order;
import com.modex.webapp.repository.OrderRepository;
import com.modex.webapp.dto.OrderRequest;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create a new purchase order
    public Order createOrder(OrderRequest request) {

        Order order = new Order();

        order.setItem(request.getItem());
        order.setQuantity(request.getQuantity());
        order.setDescription(request.getDescription());
        order.setOrderOrganization(request.getOrderOrganization());
        order.setOffice(request.getOffice());

        order.setStatus("PENDING");

        return orderRepository.save(order);
    }

    // Approve an existing purchase order
    public Order approveOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                    new RuntimeException("Order not found"));

        if (!"PENDING".equals(order.getStatus())) {
            throw new RuntimeException(
                "Only pending orders can be approved");
        }

        order.setStatus("APPROVED");

        return orderRepository.save(order);
    }
}

