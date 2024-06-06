package com.alianza.customer.controller;

import com.alianza.customer.dto.CustomerDto;
import com.alianza.customer.dto.ResponseDto;
import com.alianza.customer.service.ICustomerService;
import com.alianza.customer.utils.Constantes;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Schema(name = "Controlador Clientes", description = "Microservicio encargado de gestionar clientes")
@RequestMapping
@OpenAPIDefinition(info = @Info(title = "CustomerMS", version = "V1.0"))
public class CustomerController {


    @Autowired
    private ICustomerService customerService;

    @Operation(description = "metodo que consulta todos los clientes")
    @GetMapping(value = Constantes.URL_GET_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<CustomerDto>>> findAll() {

        ResponseDto responseDto = new ResponseDto<>();
        try {

            List<CustomerDto> listDto = customerService.findAll();
            responseDto.setError(false);
            responseDto.setMessage(Constantes.RESPONSE_OK);
            responseDto.setResponse(listDto);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {

            responseDto.setError(true);
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping(value = Constantes.URL_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Operacion que permite guardar un cliente")
    public ResponseEntity<ResponseDto> save(@RequestBody @Valid CustomerDto customer) {
        ResponseDto dto = new ResponseDto<>();
        try {
            customer.setId(customer.getId() != null ? null : customer.getId());
            dto.setResponse(customerService.saveCustomer(customer));
            dto.setError(false);
            dto.setMessage("Cliente guardado correctamente");
            return new ResponseEntity<>(dto, HttpStatus.OK);


        } catch (Exception e) {
            dto.setError(true);
            dto.setMessage("Se ha presentado el siguiente error " + e.getMessage());
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

        }


    }

    @Operation(description = "metodo que consulta por sharedkey")
    @GetMapping(value = Constantes.URL_GET_BY_SHAREDKEY, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<CustomerDto>>> findBySharedKey(@PathVariable String id) {

        ResponseDto responseDto = new ResponseDto<>();
        try {

            List<CustomerDto> listDto = customerService.findBySharedKey(id);
            responseDto.setError(false);
            responseDto.setMessage(Constantes.RESPONSE_OK);
            responseDto.setResponse(listDto);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {

            responseDto.setError(true);
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

        }

    }
}
