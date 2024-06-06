package com.alianza.customer.service;

import com.alianza.customer.dto.CustomerDto;
import com.alianza.customer.dto.ResponseDto;

import java.util.List;

public interface ICustomerService {

    public List<CustomerDto> findAll();
    public CustomerDto saveCustomer(CustomerDto customer);

    public List<CustomerDto> findBySharedKey(String sharedKey);

}
