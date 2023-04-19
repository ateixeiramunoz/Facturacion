package com.eoi.Facturacion.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private String color;

    @ManyToMany(mappedBy = "phones")
    private List<Subscription> subscriptions;

    // getters and setters
}