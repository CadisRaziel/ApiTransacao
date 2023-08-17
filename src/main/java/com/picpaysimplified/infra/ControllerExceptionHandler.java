package com.picpaysimplified.infra;

import com.picpaysimplified.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice ///Quando tudo executar e lançar a exception final o spring chama essa classe e vai ver se essa classe tem algum metodo que trata o erro lançado
public class ControllerExceptionHandler {
    ///Usuario com o mesmo documento
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Já existe um usuário com o mesmo documento.", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(DataIntegrityViolationException exception) {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
