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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc

public class CustomerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testFindAllCustomers() throws Exception {
        // Given
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setName("John");
        customer.setApellido("Doe");
        customer.setEdad(30);
        customer.setFechaNacimiento(LocalDate.of(1993, 4, 19));
        Customer customer2 = new Customer();
        customer2.setName("Mary");
        customer2.setApellido("Doe");
        customer2.setEdad(31);
        customer2.setFechaNacimiento(LocalDate.of(1992, 4, 19));
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
        Customer customer = new Customer();
        customer.setName("John");
        customer.setApellido("Doe");
        customer.setEdad(30);
        customer.setFechaNacimiento(LocalDate.of(1993, 4, 19));
        when(customerService.findById(customerId)).thenReturn(Optional.of(customer));

        // When and Then
        mockMvc.perform(get("/api/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customer.getId()))
                .andExpect(jsonPath("$.name").value(customer.getName()))
                .andExpect(jsonPath("$.apellido").value(customer.getApellido()));
    }

    @Test
    public void testSaveCustomer() throws Exception {
        // Given
        Customer customer = new Customer();
        customer.setName("John");
        customer.setApellido("Doe");
        customer.setEdad(30);
        customer.setFechaNacimiento(LocalDate.of(1993, 4, 19));
        when(customerService.save(customer)).thenReturn(new Customer(1L, "John", "Doe", 30, LocalDate.of(1993, 4, 19), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        // When and Then
        mockMvc.perform(post("/api/customer").header("Content-Type","application/json")
                        .contentType("application/json")
                        .content("{ \"name\": \"John\", \"apellido\": \"Doe\", \"edad\": 30, \"fechaNacimiento\": \"1993-04-19\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(customer.getName()))
                .andExpect(jsonPath("$.apellido").value(customer.getApellido()))
                .andExpect(jsonPath("$.edad").value(customer.getEdad()))
                .andExpect(jsonPath("$.fechaNacimiento").value(customer.getFechaNacimiento().toString()));
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

