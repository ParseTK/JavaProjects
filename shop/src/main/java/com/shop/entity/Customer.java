package com.shop.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public Customer() {}
    
    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
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
    public List<Order> getOrders() { 
        return orders; 
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
    public void setOrders(List<Order> orders) { 
        this.orders = orders; 
    }
}
