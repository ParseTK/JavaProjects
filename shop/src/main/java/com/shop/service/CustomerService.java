package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dto.Dtos.CustomerRequest;
import com.shop.dto.Dtos.CustomerResponse;
import com.shop.entity.Customer;
import com.shop.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<CustomerResponse> getAll() {
        return repo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public CustomerResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public CustomerResponse create(CustomerRequest req) {
        if (repo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already in use: " + req.getEmail());
        }

        Customer c = new Customer(
                req.getName(),
                req.getEmail(),
                req.getPhone()
        );

        return toResponse(repo.save(c));
    }

    public CustomerResponse update(Long id, CustomerRequest req) {
        Customer c = findOrThrow(id);
        c.setName(req.getName());
        c.setEmail(req.getEmail());
        c.setPhone(req.getPhone());
        return toResponse(repo.save(c));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private Customer findOrThrow(Long id) {
        Optional<Customer> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Customer not found: " + id);
        }
}


    private CustomerResponse toResponse(Customer c) {
        return new CustomerResponse(
                c.getId(),
                c.getName(),
                c.getEmail(),
                c.getPhone(),
                c.getOrders().size()
        );
    }
}
