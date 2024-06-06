package com.alianza.customer.utils;

import com.alianza.customer.dto.CustomerDto;
import com.alianza.customer.jpa.entity.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


  CustomerDto mapperToDto(CustomerEntity entity);
  CustomerEntity mapperToEntity(CustomerDto dto);

  List<CustomerDto> mapperToDtoList(List<CustomerEntity> listEntity);
}
