package com.pos.system.modules.inventory.entity;

import jakarta.persistence.*;
import lombok.Data; // Ensure you have the Lombok fix or write getters/setters manually

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Manual Getters/Setters if Lombok fails
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}