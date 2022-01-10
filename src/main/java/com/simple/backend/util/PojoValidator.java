package com.simple.backend.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
@RequiredArgsConstructor
public class PojoValidator {

    private final Validator validator;

    public void validate(Object pojo) {
        validator.validate(pojo)
                .stream()
                .map(cv -> cv.getPropertyPath() + " " + cv.getMessage())
                .reduce((a, b) -> String.join(" and ", a, b))
                .ifPresent(this::throwException);

    }

    private void throwException(String message) {
        throw new RuntimeException(message);
    }
}
