package com.eoi.Facturacion.entities;

import jakarta.persistence.*;

import java.util.List;


/**

 Clase que representa un teléfono móvil en la base de datos.
 */
@Entity // indica que esta clase es una entidad que se almacenará en una tabla de la base de datos
@Table(name = "phone") // indica el nombre de la tabla en la base de datos que almacena esta entidad
public class Phone {

    /**

     ID del teléfono.
     */
    @Id // indica que esta propiedad es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que la generación del valor de la clave primaria será a través de una columna autoincremental en la base de datos
    private Long id;
    /**

     Marca del teléfono.
     */
    private String brand;
    /**

     Modelo del teléfono.
     */
    private String model;
    /**

     Color del teléfono.
     */
    private String color;
    /**

     Lista de suscripciones asociadas al teléfono.
     */
    @ManyToMany(mappedBy = "phones") // indica la relación many-to-many con la entidad Subscription, donde "phones" es el nombre de la propiedad en Subscription que mapea esta relación
    private List<Subscription> subscriptions;
    /**

     Devuelve el ID del teléfono.
     @return el ID del teléfono
     */
    public Long getId() {
        return id;
    }
    /**

     Establece el ID del teléfono.
     @param id el ID del teléfono
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**

     Devuelve la marca del teléfono.
     @return la marca del teléfono
     */
    public String getBrand() {
        return brand;
    }
    /**

     Establece la marca del teléfono.
     @param brand la marca del teléfono
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    /**

     Devuelve el modelo del teléfono.
     @return el modelo del teléfono
     */
    public String getModel() {
        return model;
    }
    /**

     Establece el modelo del teléfono.
     @param model el modelo del teléfono
     */
    public void setModel(String model) {
        this.model = model;
    }
    /**

     Devuelve el color del teléfono.
     @return el color del teléfono
     */
    public String getColor() {
        return color;
    }
    /**

     Establece el color del teléfono.
     @param color el color del teléfono
     */
    public void setColor(String color) {
        this.color = color;
    }
    /**

     Devuelve la lista de suscripciones asociadas al teléfono.
     @return la lista de suscripciones asociadas al teléfono
     */
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }
    /**

     Establece la lista de suscripciones asociadas al teléfono.
     @param subscriptions la lista de suscripciones asociadas al teléfono
     */
    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}