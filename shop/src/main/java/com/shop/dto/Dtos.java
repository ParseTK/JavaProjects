package com.shop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.shop.entity.Order;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

// Customer DTOs
public class Dtos {

    public static class CustomerRequest {
        @NotBlank private String name;
        @Email @NotBlank private String email;
        private String phone;

        public CustomerRequest() {}

        public CustomerRequest(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        }


        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }


        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }


    public static class CustomerResponse {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private int totalOrders;

        public CustomerResponse() {}
        
        public CustomerResponse(Long id, String name, String email, String phone, int totalOrders) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.totalOrders = totalOrders;
        }


        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public int getTotalOrders() {
            return totalOrders;
        }


        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setTotalOrders(int totalOrders) {
            this.totalOrders = totalOrders;
        }
    }

    // Product DTOs
    public static class ProductRequest {
        @NotBlank private String name;
        private String description;
        @Positive private BigDecimal price;
        @PositiveOrZero private Integer stock;

        public ProductRequest() {}

        public ProductRequest(String name, String description, BigDecimal price, Integer stock) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.stock = stock;
        }

    
        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public Integer getStock() {
            return stock;
        }


        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }
    }

    public static class ProductResponse {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stock;

        public ProductResponse() {}

        public ProductResponse(Long id, String name, String description, BigDecimal price, Integer stock) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.stock = stock;
        }


        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public Integer getStock() {
            return stock;
        }


        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }
    }

    // Order DTOs
    public static class OrderRequest {
        @NotNull private Long customerId;
        @NotEmpty private List<OrderItemRequest> items;

        public OrderRequest() {}

        public OrderRequest(Long customerId, List<OrderItemRequest> items) {
            this.customerId = customerId;
            this.items = items;
        }


        public Long getCustomerId() {
            return customerId;
        }

        public List<OrderItemRequest> getItems() {
            return items;
        }


        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public void setItems(List<OrderItemRequest> items) {
            this.items = items;
        }
    }

    public static class OrderResponse {
        private Long id;
        private Long customerId;
        private String customerName;
        private List<OrderItemResponse> items;
        private BigDecimal total;
        private Order.OrderStatus status;
        private LocalDateTime createdAt;

        public OrderResponse() {}

        public OrderResponse(Long id, 
            Long customerId, 
            String customerName, 
            List<OrderItemResponse> 
            items, BigDecimal total, 
            Order.OrderStatus status, 
            LocalDateTime createdAt) {
                
            this.id = id;
            this.customerId = customerId;
            this.customerName = customerName;
            this.items = items;
            this.total = total;
            this.status = status;
            this.createdAt = createdAt;
        }


        public Long getId() {
            return id;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public List<OrderItemResponse> getItems() {
            return items;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public Order.OrderStatus getStatus() {
            return status;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }


        public void setId(Long id) {
            this.id = id;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public void setItems(List<OrderItemResponse> items) {
            this.items = items;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public void setStatus(Order.OrderStatus status) {
            this.status = status;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

    }

    // OrderItem DTOs
    public static class OrderItemRequest {
        @NotNull private Long productId;
        @Positive private Integer quantity;

        public OrderItemRequest() {}

        public OrderItemRequest(Long productId, Integer quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        
        public Long getProductId() {
            return productId;
        }

        public Integer getQuantity() {
            return quantity;
        }


        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    public static class OrderItemResponse {
        private Long id;
        private Long productId;
        private String productName;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal subtotal;

        public OrderItemResponse() {}

        public OrderItemResponse(Long id, Long productId, String productName, Integer quantity, BigDecimal unitPrice, BigDecimal subtotal) {
            this.id = id;
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.subtotal = subtotal;
        }


        public Long getId() {
            return id;
        }

        public Long getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        
        public void setId(Long id) {
            this.id = id;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
        }
    }
}
