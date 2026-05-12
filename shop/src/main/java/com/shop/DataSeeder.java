package com.shop;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shop.dto.Dtos.CustomerRequest;
import com.shop.dto.Dtos.CustomerResponse;
import com.shop.dto.Dtos.OrderItemRequest;
import com.shop.dto.Dtos.OrderRequest;
import com.shop.dto.Dtos.ProductRequest;
import com.shop.dto.Dtos.ProductResponse;
import com.shop.service.CustomerService;
import com.shop.service.OrderService;
import com.shop.service.ProductService;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;

    public DataSeeder(CustomerService customerService, ProductService productService, OrderService orderService) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) {

        // Customers
        CustomerResponse durk = customerService.create(
                new CustomerRequest("Durk Jettson", "durkJjettson@gmail.com", "555-5555")
        );

        CustomerResponse bob = customerService.create(
                new CustomerRequest("Billy Bob Smith", "bob@gmail.com", "555-1111")
        );

        
        // Products
        ProductResponse vinylPanel = productService.create(
                new ProductRequest("6x8 White Vinyl Fence Panels", "High grade thick vinyl panels that are durable and weather-resistant",
                        new BigDecimal("220.00"), 240)
        );

        ProductResponse woodPanel = productService.create(
                new ProductRequest("6x8 Wood Fence Panels", "Beautiful cedar wood panels that are sturdy and long-lasting",
                        new BigDecimal("120.00"), 200)
        );

        ProductResponse aluminiumPanel = productService.create(
                new ProductRequest("4x6 Aluminium Fence Panels", "Durable aluminium panels for a modern look",
                        new BigDecimal("300.00"), 150)
        );

        ProductResponse chainlinkRoll = productService.create(
                new ProductRequest("50ft Chain Link Roll", "Durable chain link roll for a modern look",
                        new BigDecimal("550.00"), 30)
        );


        // Orders
        orderService.create(
                new OrderRequest(
                        durk.getId(),
                        List.of(
                                new OrderItemRequest(vinylPanel.getId(), 1),
                                new OrderItemRequest(chainlinkRoll.getId(), 2)
                        )
                )
        );

        orderService.create(
                new OrderRequest(
                        bob.getId(),
                        List.of(
                                new OrderItemRequest(aluminiumPanel.getId(), 15),
                                new OrderItemRequest(woodPanel.getId(), 6)
                        )
                )
        );
    }
}
