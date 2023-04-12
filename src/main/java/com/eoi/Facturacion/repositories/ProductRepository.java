package com.eoi.Facturacion.repositories;
import com.eoi.Facturacion.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}