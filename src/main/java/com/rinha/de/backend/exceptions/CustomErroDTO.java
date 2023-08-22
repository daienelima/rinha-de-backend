package com.rinha.de.backend.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomErroDTO {
    private final int status;
    private final String error;
    private final String message;
}
