package com.alianza.customer.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {

    private boolean isError;

    private String message;

    private T response;
}
