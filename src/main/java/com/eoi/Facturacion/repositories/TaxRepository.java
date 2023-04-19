package com.eoi.Facturacion.repositories;

import com.eoi.Facturacion.entities.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, Long> {

}
