package com.example.gestor.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

class TareaPatchRequestTest {

    @Test
    void patchRequestAceptaValoresValidos() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        TareaPatchRequest request = new TareaPatchRequest();
        request.setTitulo("Revisar documentación");
        request.setPrioridad("media");
        request.setCompletada(true);

        Set<ConstraintViolation<TareaPatchRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }
}
