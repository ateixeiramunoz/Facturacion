package com.eoi.Facturacion.repositories;

import com.eoi.Facturacion.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

}
