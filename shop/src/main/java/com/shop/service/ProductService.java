package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dto.Dtos.*;
import com.shop.dto.Dtos.ProductRequest;
import com.shop.dto.Dtos.ProductResponse;
import com.shop.entity.Product;
import com.shop.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<ProductResponse> getAll() {
        return repo.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    public ProductResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public List<ProductResponse> search(String name) {
        return repo.findByNameContainingIgnoreCase(name).stream()
        .map(this::toResponse)
        .toList();
    }

    public ProductResponse create(ProductRequest req) {
        Product p = new Product();
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setPrice(req.getPrice());
        if (req.getStock() != null) {
            p.setStock(req.getStock());
        } else {
            p.setStock(0);
        }
        return toResponse(repo.save(p));
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(
            p.getId(),
            p.getName(),
            p.getDescription(),
            p.getPrice(),
            p.getStock()
        );
    }

    public ProductResponse update(Long id, ProductRequest req) {
        Product p = findOrThrow(id);
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setPrice(req.getPrice());
        p.setStock(req.getStock());
        return toResponse(repo.save(p));
    }
    
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Product findOrThrow(Long id) {
        Optional<Product> optional = repo.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("Product not found: " + id);
        }

        return optional.get();
    }

}
