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
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        Order order = new Order();

        order.setItem(request.getItem());
        order.setQuantity(request.getQuantity());
        order.setDescription(request.getDescription());
        order.setOrderOrganization(request.getOrderOrganization());
        order.setOffice(request.getOffice());

        order.setStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }
    
    @PutMapping("/{orderId}/status")  
    public ResponseEntity<Order> approveOrder(@PathVariable Long orderId) {
        // 1. Find the order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        // 2. Approve it - status is already set up in your entity
        order.setStatus(OrderStatus.APPROVED);

        // 3. Save it back to DB
        Order savedOrder = orderRepository.save(order);

        // 4. Return it with 200 OK
        return ResponseEntity.ok(savedOrder);
    }
}
