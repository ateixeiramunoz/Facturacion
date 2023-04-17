package com.eoi.Facturacion.services;

import com.eoi.Facturacion.entities.Customer;
import com.eoi.Facturacion.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}