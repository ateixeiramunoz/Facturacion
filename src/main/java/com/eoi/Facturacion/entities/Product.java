package com.eoi.Facturacion.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    /**
     * Identificador único del producto.
     */
    @Id // Indica que esta propiedad es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que la estrategia de generación de la clave es autoincremental
    private Long id;

    /**
     * Nombre del producto.
     */
    private String name;

    /**
     * Descripción del producto.
     */
    private String description;

    /**
     * Precio del producto.
     */
    @Column(name = "price") // Indica que esta propiedad está mapeada a la columna "price" de la tabla correspondiente
    private Double price;

    // getters and setters
}

