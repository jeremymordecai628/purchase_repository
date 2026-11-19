package com.modex.webapp.controller;

import com.modex.webapp.model.Order;
import com.modex.webapp.dto.OrderRequest;
import com.modex.webapp.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Submit a new purchase order
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderRequest request) {

        Order order = orderService.createOrder(request);

        return ResponseEntity.status(201).body(order);
    }

    // Approve a purchase order
    @PostMapping("/{orderId}/approve")
    public ResponseEntity<Order> approveOrder(
            @PathVariable Long orderId) {

        Order order = orderService.approveOrder(orderId);

        return ResponseEntity.ok(order);
    }
}

