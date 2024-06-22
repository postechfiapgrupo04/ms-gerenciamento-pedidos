package br.com.fiap.mspedidos.application.rest.controller;

import br.com.fiap.mspedidos.application.rest.dto.ErrorDTO;
import br.com.fiap.mspedidos.domain.exception.AppException;
import jakarta.persistence.MappedSuperclass;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public class AbstractRestController {

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(new ErrorDTO(HttpStatus.PRECONDITION_FAILED.value(), error.getDefaultMessage()));
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(AppException.class)
    public ErrorDTO handleValidationExceptions(AppException ex) {
        return new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

}
