package com.eoi.Facturacion;

import com.eoi.Facturacion.controllers.CustomerController;
import com.eoi.Facturacion.entities.Contract;
import com.eoi.Facturacion.entities.Customer;
import com.eoi.Facturacion.entities.Invoice;
import com.eoi.Facturacion.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Juan");
        customer.setApellido("Perez");
        customer.setEdad(25);
        customer.setFechaNacimiento(LocalDate.of(1998, 3, 15));
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer();
        customer.setName("John");
        customer.setApellido("Doe");
        customer.setEdad(30);
        customer.setFechaNacimiento(LocalDate.of(1992, 4, 19));
        return customer;
    }

    @Test
    public void testShowCustomers() throws Exception {
        when(customerService.findAll()).thenReturn(Arrays.asList(customer));
        mockMvc.perform(get("/customers/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("dataObject", hasProperty("size", is(1))))
                .andExpect(model().attribute("dataObject", hasProperty("[0].id", is(1L))))
                .andExpect(model().attribute("dataObject", hasProperty("[0].name", is("Juan"))))
                .andExpect(model().attribute("dataObject", hasProperty("[0].apellido", is("Perez"))))
                .andExpect(model().attribute("dataObject", hasProperty("[0].edad", is(25))))
                .andExpect(model().attribute("dataObject", hasProperty("[0].fechaNacimiento", is(LocalDate.of(1998, 3, 15)))));
    }

    @Test
    public void testShowNewCustomerForm() throws Exception {
        mockMvc.perform(get("/customers/new"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("customer", hasProperty("id", is((Long) null))))
                .andExpect(model().attribute("customer", hasProperty("name", is((String) null))))
                .andExpect(model().attribute("customer", hasProperty("apellido", is((String) null))))
                .andExpect(model().attribute("customer", hasProperty("edad", is((Integer) null))))
                .andExpect(model().attribute("customer", hasProperty("fechaNacimiento", is((LocalDate) null))))
                .andExpect(view().name("index"))
                .andExpect(model().attribute("fragmentName", is("fragment-customer-form")));
    }

    @Test
    public void testSaveCustomer() throws Exception {
        mockMvc.perform(post("/customers/save")
                        .param("name", "Juan")
                        .param("apellido", "Perez")
                        .param("edad", "25")
                        .param("fechaNacimiento", "1998-03-15"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers/"));
    }


    @Test
    public void testShowEditCustomerForm() throws Exception {
        Customer customer = createTestCustomer();
        mockMvc.perform(get("/customers/edit/" + customer.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customer-form"))
                .andExpect(model().attributeExists("customer"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Customer customer = createTestCustomer();
        mockMvc.perform(get("/customers/delete/" + customer.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers/"));

        // Verificar que el cliente haya sido eliminado
        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
        assertFalse(optionalCustomer.isPresent());
    }

    @Test
    public void testNewInvoiceForm() throws Exception {
        Customer customer = createTestCustomer();
        mockMvc.perform(get("/customers/" + customer.getId() + "/invoices/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customer-invoice-form"))
                .andExpect(model().attributeExists("invoice"))
                .andExpect(model().attribute("invoice", hasProperty("customer", equalTo(customer))));
    }

    @Test
    public void testCreateInvoice() throws Exception {
        Customer customer = createTestCustomer();
        Invoice invoice = createTestInvoice(customer);

        mockMvc.perform(post("/customers/" + customer.getId() + "/invoices/new")
                        .param("description", invoice.getDescription())
                        .param("amount", invoice.getAmount().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers/edit/" + customer.getId()));

        // Verificar que la factura se haya creado y esté asociada al cliente
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoice.getId());
        assertTrue(optionalInvoice.isPresent());
        assertEquals(customer.getId(), optionalInvoice.get().getCustomer().getId());
    }

    @Test
    public void testNewContractForm() throws Exception {
        Customer customer = createTestCustomer();
        mockMvc.perform(get("/customers/" + customer.getId() + "/contracts/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customer-contract-form"))
                .andExpect(model().attributeExists("contract"))
                .andExpect(model().attribute("contract", hasProperty("customer", equalTo(customer))));
    }
/*
    @Test
    public void testCreateContract() throws Exception {
        Customer customer = createTestCustomer();
        Contract contract = createTestContract(customer);

        mockMvc.perform(post("/customers/" + customer.getId() + "/contracts/new")
                        .param("description", contract.getDescription())
                        .param("startDate", contract.getStartDate().toString())
                        .param("endDate", contract.getEndDate().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers/edit/" + customer.getId()));

        // Verificar que el contrato se haya creado y esté asociado al cliente
        Optional<Contract> optionalContract = contractRepository.findById(contract.getId());
        assertTrue(optionalContract.isPresent());
        assertEquals(customer.getId(), optionalContract.get().getCustomer().getId());
    }
*/
}
