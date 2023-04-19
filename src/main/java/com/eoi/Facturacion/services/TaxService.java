package com.eoi.Facturacion.services;

import com.eoi.Facturacion.entities.Tax;
import com.eoi.Facturacion.repositories.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxService {

    @Autowired
    private TaxRepository taxRepository;

    public List<Tax> findAll() {
        return taxRepository.findAll();
    }

    public Optional<Tax> findById(Long id) {
        return taxRepository.findById(id);
    }

    public void save(Tax tax) {
        taxRepository.save(tax);
    }

    public void deleteById(Long id) {
        taxRepository.deleteById(id);
    }

}