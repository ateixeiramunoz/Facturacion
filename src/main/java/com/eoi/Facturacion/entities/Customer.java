package com.eoi.Facturacion.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    /**
     * Identificador único del cliente.
     */
    @Id // indica que esta propiedad es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que la estrategia de generación de la clave es autoincremental
    private Long id;

    /**
     * Nombre del cliente.
     */
    private String name;

    /**
     * Apellido del cliente.
     */
    private String apellido;

    /**
     * Edad del cliente.
     */
    private Integer edad;

    /**
     * Fecha de nacimiento del cliente.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd") // indica el formato en que se espera la fecha
    private LocalDate fechaNacimiento;

    /**
     * Lista de facturas asociadas al cliente.
     */
    @JsonManagedReference // se usa para evitar ciclos infinitos al serializar la entidad
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // indica la relación OneToMany con Invoice
    // mappedBy indica que esta clase no es dueña de la relación y que Invoice es la clase dueña de la misma
    // fetch indica que la lista de facturas se cargará de forma "lazy"
    // cascade indica que las operaciones de persistencia, eliminación y actualización se propagarán a las facturas relacionadas
    private List<Invoice> invoices;

    /**
     * Lista de suscripciones asociadas al cliente.
     */
    @JsonManagedReference // se usa para evitar ciclos infinitos al serializar la entidad
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // indica la relación OneToMany con Subscription
    // mappedBy indica que esta clase no es dueña de la relación y que Subscription es la clase dueña de la misma
    // fetch indica que la lista de suscripciones se cargará de forma "lazy"
    // cascade indica que las operaciones de persistencia, eliminación y actualización se propagarán a las suscripciones relacionadas
    private List<Subscription> subscriptions;

    /**
     * Lista de contratos asociados al cliente.
     */
    @JsonManagedReference // se usa para evitar ciclos infinitos al serializar la entidad
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // indica la relación OneToMany con Contract
    // mappedBy indica que esta clase no es dueña de la relación y que Contract es la clase dueña de la misma
    // fetch indica que la lista de contratos se cargará de forma "lazy"
    // cascade indica que las operaciones de persistencia, eliminación y actualización se propagarán a los contratos relacionados
    private List<Contract> contracts;

    // getters y setters
}






// org.hibernate.HibernateException: A collection with cascade="all-delete-orphan" was no longer referenced by the owning entity instance: com.eoi.Facturacion.entities.Customer.invoiceList