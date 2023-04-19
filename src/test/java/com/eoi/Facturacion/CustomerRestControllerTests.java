package com.eoi.Facturacion.controllers;

import com.eoi.Facturacion.entities.Customer;
import com.eoi.Facturacion.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testFindAllCustomers() throws Exception {
        // Given
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "John", "Doe"));
        customers.add(new Customer(2L, "Jane", "Doe"));
        when(customerService.findAll()).thenReturn(customers);

        // When and Then
        mockMvc.perform(get("/api/customer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(customers.size()));
    }

    @Test
    public void testFindCustomerById() throws Exception {
        // Given
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "John", "Doe");
        when(customerService.findById(customerId)).thenReturn(Optional.of(customer));

        // When and Then
        mockMvc.perform(get("/api/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customer.getId()))
                .andExpect(jsonPath("$.firstName").value(customer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(customer.getLastName()));
    }

    @Test
    public void testSaveCustomer() throws Exception {
        // Given
        Customer customer = new Customer(null, "John", "Doe");
        when(customerService.save(customer)).thenReturn(new Customer(1L, "John", "Doe"));

        // When and Then
        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value(customer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(customer.getLastName()));
    }

    @Test
    public void testDeleteCustomerById() throws Exception {
        // Given
        Long customerId = 1L;

        // When and Then
        mockMvc.perform(delete("/api/customer/{id}", customerId))
                .andExpect(status().isOk());

    }
}

