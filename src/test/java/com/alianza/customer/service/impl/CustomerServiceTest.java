package com.alianza.customer.service.impl;

import com.alianza.customer.dto.CustomerDto;
import com.alianza.customer.jpa.entity.CustomerEntity;
import com.alianza.customer.jpa.repository.CustomerRepository;
import com.alianza.customer.utils.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerService customerService;

    private CustomerEntity customerEntity;
    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setSharedKey("key1");
        customerEntity.setBusinessId("B123");
        customerEntity.setEmail("test@example.com");
        customerEntity.setPhone("1234567890");

        customerDto = new CustomerDto();
        customerDto.setId(2L);
        customerDto.setSharedKey("key1");
        customerDto.setBusinessId("B123");
        customerDto.setEmail("test@example.com");
        customerDto.setPhone("1234567890");
    }

    @Test
    void testFindAll() {
        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customerEntity));
        when(mapper.mapperToDtoList(any())).thenReturn(Collections.singletonList(customerDto));

        List<CustomerDto> result = customerService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(customerRepository, times(1)).findAll();
        verify(mapper, times(1)).mapperToDtoList(any());
    }

    @Test
    void testSaveCustomer() {
        when(mapper.mapperToEntity(any())).thenReturn(customerEntity);
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);
        when(mapper.mapperToDto(any(CustomerEntity.class))).thenReturn(customerDto);

        CustomerDto result = customerService.saveCustomer(customerDto);

        assertNotNull(result);
        assertEquals(customerDto.getId(), result.getId());
        verify(mapper, times(1)).mapperToEntity(any());
        verify(customerRepository, times(1)).save(any(CustomerEntity.class));
        verify(mapper, times(1)).mapperToDto(any(CustomerEntity.class));
    }

    @Test
    void testFindBySharedKey() {
        when(customerRepository.findBySharedKey(anyString())).thenReturn(Collections.singletonList(customerEntity));
        when(mapper.mapperToDtoList(any())).thenReturn(Collections.singletonList(customerDto));

        List<CustomerDto> result = customerService.findBySharedKey("key1");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(customerRepository, times(1)).findBySharedKey(anyString());
        verify(mapper, times(1)).mapperToDtoList(any());
    }
}
