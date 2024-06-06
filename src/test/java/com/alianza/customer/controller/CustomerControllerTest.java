package com.alianza.customer.controller;

import com.alianza.customer.dto.CustomerDto;
import com.alianza.customer.service.ICustomerService;
import com.alianza.customer.utils.Constantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICustomerService customerService;

    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setSharedKey("key1");
        customerDto.setBusinessId("B123");
        customerDto.setEmail("test@example.com");
        customerDto.setPhone("1234567890");
    }

    @Test
    void testFindAll() throws Exception {
        when(customerService.findAll()).thenReturn(Collections.singletonList(customerDto));

        mockMvc.perform(get(Constantes.URL_GET_ALL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is(false)))
                .andExpect(jsonPath("$.message", is(Constantes.RESPONSE_OK)))
                .andExpect(jsonPath("$.response", hasSize(1)))
                .andExpect(jsonPath("$.response[0].id", is(customerDto.getId().intValue())));
    }

    @Test
    void testSaveCustomer() throws Exception {
        when(customerService.saveCustomer(any(CustomerDto.class))).thenReturn(customerDto);

        mockMvc.perform(post(Constantes.URL_SAVE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sharedKey\":\"key1\", \"businessId\":\"B123\", \"email\":\"test@example.com\", \"phone\":\"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is(false)))
                .andExpect(jsonPath("$.message", is("Cliente guardado correctamente")))
                .andExpect(jsonPath("$.response.id", is(customerDto.getId().intValue())));
    }

    @Test
    void testFindBySharedKey() throws Exception {
        when(customerService.findBySharedKey("key1")).thenReturn(Collections.singletonList(customerDto));

        mockMvc.perform(get(Constantes.URL_GET_BY_SHAREDKEY, "key1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is(false)))
                .andExpect(jsonPath("$.message", is(Constantes.RESPONSE_OK)))
                .andExpect(jsonPath("$.response", hasSize(1)))
                .andExpect(jsonPath("$.response[0].id", is(customerDto.getId().intValue())));
    }
}