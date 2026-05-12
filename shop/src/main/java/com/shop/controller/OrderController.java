package com.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.Dtos.OrderRequest;
import com.shop.dto.Dtos.OrderResponse;
import com.shop.entity.Order;
import com.shop.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Manage orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List all orders")
    public List<OrderResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public OrderResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get orders by customer")
    public List<OrderResponse> getByCustomer(@PathVariable Long customerId) {
        return service.getByCustomer(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new order")
    public OrderResponse create(@Valid @RequestBody OrderRequest req) {
        return service.create(req);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update order status")
    public OrderResponse updateStatus(@PathVariable Long id, @RequestParam Order.OrderStatus status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an order")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
