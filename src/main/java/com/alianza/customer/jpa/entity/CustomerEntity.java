package com.alianza.customer.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


import java.util.Date;

@Entity(name = "Customer")
@Data
public class CustomerEntity {

    /**
     * identificador unico
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * nombre de usuario
     */
    private String sharedKey;
    /**
     * id de negocio
     */

    private String businessId;

    /**
     * correo electronic
     */
    private String email;
    /**
     * telefono
     */
    private String phone;

    /**
     * fecha de agregado
     */

    private Date dateAdded;

}
