package com.eoi.Facturacion.controllers;

import com.eoi.Facturacion.entities.Customer;
import com.eoi.Facturacion.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@RestController  //Genera una API REST
@RequestMapping("/api/customer")  //url que te devuelve la lista de customers

public class CustomerRestController {

    @Autowired

    private CustomerService customerService;

    @GetMapping

    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {

        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent())
        {
            return customer.get();
        }
        return null;
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
    }

}