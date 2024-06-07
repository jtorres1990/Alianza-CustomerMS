package com.alianza.customer.service.impl;

import com.alianza.customer.dto.CustomerDto;
import com.alianza.customer.dto.ResponseDto;
import com.alianza.customer.jpa.entity.CustomerEntity;
import com.alianza.customer.jpa.repository.CustomerRepository;
import com.alianza.customer.service.ICustomerService;
import com.alianza.customer.utils.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper mapper;

    /**
     *
     * @return listado de clientes
     */
    @Override
    public List<CustomerDto> findAll() {
        try {
            log.info("Consultando los clientes");
            List<CustomerEntity> listEntidades = customerRepository.findAll();
            log.info("Se obtuvieron " + listEntidades.size() + " clientes");
            log.info("Mapear lista");
            List<CustomerDto> listaDto = mapper.mapperToDtoList(listEntidades);
            return listaDto;
        } catch (Exception e) {
            log.error("Se ha presentado el siguiente error " + e.getMessage());
            return null;
        }

    }

    /**
     *
     * @param customer para guardar
     * @return custoemr guardado
     */
    @Override
    public CustomerDto saveCustomer(CustomerDto customer) {


        try {

            log.info("Mapenado dto a entidad "+ customer.toString());
            CustomerEntity entity = mapper.mapperToEntity(customer);
            log.info("Guardando entidad");
            customerRepository.save(entity);
            log.info("Entidad Guardada"+ entity);
            customer = mapper.mapperToDto(entity);
            return customer;
        } catch (Exception e) {
            log.error("Se ha presentado el siguiente error " + e.getMessage());

            return null;
        }

    }

    /**
     *
     * @param filtro de busqeuda sharedKey
     * @return listado de coincidencia
     */

    @Override
    public List<CustomerDto> findBySharedKey(String sharedKey) {

        try {
            log.info("Consultando por sharedKey " + sharedKey);
            List<CustomerEntity> listEntity = customerRepository.findBySharedKey(sharedKey);
            log.info("Mapeando a dto " + listEntity.toString());
            List<CustomerDto> dto = mapper.mapperToDtoList(listEntity);
            return dto;


        } catch (Exception e) {
            log.error("Se ha presentado el siguiente error " + e.getMessage());

        }
        return null;
    }
}
