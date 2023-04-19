package com.eoi.Facturacion.repositories;
import com.eoi.Facturacion.entities.Invoice;
import com.eoi.Facturacion.entities.InvoiceTax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceTaxRepository extends JpaRepository<InvoiceTax, Long> {

}