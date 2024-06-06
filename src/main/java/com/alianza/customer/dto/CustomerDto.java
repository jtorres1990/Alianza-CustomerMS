package com.alianza.customer.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {


    @Schema(description = "Id del registro", required = false, example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "nombre de usuario del cliente", required = true, example = "jtorres")
    @JsonProperty("sharedKey")
    private String sharedKey;

    @Schema(description = "id de negocio ", required = true, example = "john torres")
    @JsonProperty("businessId")
    private String businessId;

    @Schema(description = "correo electronico ", required = true, example = "john.torres@gmail.com")
    @JsonProperty("email")
    private String email;

    @Schema(description = "telefono celular", required = true, example = "3001234456")
    @JsonProperty("phone")
    private String phone;

    @Schema(description = "fecha de agregado", required = true, example = "05/06/2024")
    @JsonProperty("dateAdded")
    private Date dateAdded;

}
