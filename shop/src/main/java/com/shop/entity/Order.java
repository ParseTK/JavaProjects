package com.shop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Order() {}

    public Order(Customer customer, List<OrderItem> items, OrderStatus status) {
        this.customer = customer;
        this.items = items;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    
    public Long getId() { 
        return id; 
    }

    public Customer getCustomer() { 
        return customer; 
    }

    public List<OrderItem> getItems() { 
        return items; 
    }

    public OrderStatus getStatus() { 
        return status; 
    }

    public LocalDateTime getCreatedAt() { 
        return createdAt; 
    }


    public void setCustomer(Customer customer) { 
        this.customer = customer; 
    }

    public void setItems(List<OrderItem> items) { 
        this.items = items; 
    }

    public void setStatus(OrderStatus status) { 
        this.status = status; 
    }
    
    public void setCreatedAt(LocalDateTime createdAt) { 
        this.createdAt = createdAt; 
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public enum OrderStatus {
        PENDING, 
        CONFIRMED, 
        SHIPPED, 
        DELIVERED, 
        CANCELLED
    }
}
