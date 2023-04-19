package com.eoi.Facturacion.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


/**
 * Clase que representa un contrato.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    /**
     * Identificador único del contrato.
     */
    @Id // indica que esta propiedad es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que la estrategia de generación de la clave es autoincremental
    private Long id;

    /**
     * Fecha de inicio del contrato.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd") // indica el formato en que se espera la fecha
    private LocalDate startDate;

    /**
     * Fecha de fin del contrato.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd") // indica el formato en que se espera la fecha
    private LocalDate endDate;

    /**
     * Cliente asociado al contrato.
     */
    @ManyToOne(fetch = FetchType.EAGER) // indica la relación many-to-one con la entidad Customer, con carga inmediata (EAGER)
    @JoinColumn(name = "customer_id") // indica el nombre de la columna en la tabla que almacena la relación
    private Customer customer;

    /**
     * Lista de suscripciones asociadas al contrato.
     */
    @ManyToMany // indica la relación many-to-many con la entidad Subscription
    @JoinTable(
            name = "contract_subscription", // indica el nombre de la tabla intermedia que almacena la relación
            joinColumns = @JoinColumn(name = "contract_id"), // indica el nombre de la columna que almacena la clave primaria de Contract en la tabla intermedia
            inverseJoinColumns = @JoinColumn(name = "subscription_id")) // indica el nombre de la columna que almacena la clave primaria de Subscription en la tabla intermedia
    private List<Subscription> subscriptions;

    /**
     * Obtiene el identificador único del contrato.
     *
     * @return el identificador único del contrato
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del contrato.
     *
     * @param id el identificador único del contrato
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha de inicio del contrato.
     *
     * @return la fecha de inicio del contrato
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Establece la fecha de inicio del contrato.
     *
     * @param startDate la fecha de inicio del contrato
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Obtiene la fecha de fin del contrato.
     *
     * @return la fecha de fin del contrato
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Establece la fecha de fin del contrato.
     *
     * @param endDate la fecha de fin del contrato
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Obtiene el cliente asociado al contrato.
     *
     * @return el cliente asociado al contrato
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Establece el cliente asociado al contrato.
     *
     * @param customer el cliente asociado al contrato
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Indica la relación many-to-many con la entidad Subscription.
     *
     * @return la lista de suscripciones asociadas al contrato.
     */
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    /**
     * Establece la lista de suscripciones asociadas al contrato.
     *
     * @param subscriptions la nueva lista de suscripciones asociadas al contrato.
     */
    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
