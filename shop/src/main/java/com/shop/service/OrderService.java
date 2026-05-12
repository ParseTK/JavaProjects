package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dto.Dtos.OrderItemRequest;
import com.shop.dto.Dtos.OrderItemResponse;
import com.shop.dto.Dtos.OrderRequest;
import com.shop.dto.Dtos.OrderResponse;
import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.entity.Product;
import com.shop.repository.CustomerRepository;
import com.shop.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepo;
    private final CustomerRepository customerRepo;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepo,
                        CustomerRepository customerRepo,
                        ProductService productService) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.productService = productService;
    }

    public List<OrderResponse> getAll() {
        return orderRepo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public OrderResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public List<OrderResponse> getByCustomer(Long customerId) {
        return orderRepo.findByCustomerId(customerId).stream()
                .map(this::toResponse)
                .toList();
    }

    public OrderResponse create(OrderRequest req) {

        Optional<Customer> optional = customerRepo.findById(req.getCustomerId());

        if (optional.isEmpty()) {
            throw new RuntimeException("Customer not found: " + req.getCustomerId());
        }

        Customer customer = optional.get();

        Order order = new Order();
        order.setCustomer(customer);
        order.setItems(new ArrayList<>());

        for (OrderItemRequest itemReq : req.getItems()) {

            Product product = productService.findOrThrow(itemReq.getProductId());

            if (product.getStock() < itemReq.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for: " + product.getName());
            }

            product.setStock(product.getStock() - itemReq.getQuantity());

            OrderItem item = new OrderItem(
                    order,
                    product,
                    itemReq.getQuantity(),
                    product.getPrice()
            );

            order.getItems().add(item);
        }

        return toResponse(orderRepo.save(order));
    }

    public OrderResponse updateStatus(Long id, Order.OrderStatus status) {
        Order order = findOrThrow(id);
        order.setStatus(status);
        return toResponse(orderRepo.save(order));
    }

    public void delete(Long id) {
        orderRepo.deleteById(id);
    }

    private Order findOrThrow(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    private OrderResponse toResponse(Order o) {

        List<OrderItemResponse> items = o.getItems().stream()
                .map(i -> new OrderItemResponse(
                        i.getId(),
                        i.getProduct().getId(),
                        i.getProduct().getName(),
                        i.getQuantity(),
                        i.getUnitPrice(),
                        i.getSubtotal()
                ))
                .toList();

        return new OrderResponse(
                o.getId(),
                o.getCustomer().getId(),
                o.getCustomer().getName(),
                items,
                o.getTotal(),
                o.getStatus(),
                o.getCreatedAt()
        );
    }
}
