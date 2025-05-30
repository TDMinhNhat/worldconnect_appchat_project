package dev.skyherobrine.project.backend.exceptions.advices;

import dev.skyherobrine.project.backend.exceptions.RequestParamException;
import dev.skyherobrine.project.backend.models.Response;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ResourceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleInvalidModelException(MethodArgumentNotValidException ex) {
        log.error("Resource Exception: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.ok(new Response(
                HttpStatus.BAD_REQUEST.value(),
                "Error in model validation",
                errors
        ));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequestParamException.class)
    public ResponseEntity<Response> handleRequestParamException(RequestParamException ex) {
        log.error("Resource Exception: {}", ex.getMessage());
        return ResponseEntity.ok(new Response(
                HttpStatus.BAD_REQUEST.value(),
                "Pass the parameter into the request error",
                ex.getMessage()
        ));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response> handleObjectNotFoundException(EntityNotFoundException ex) {
        log.warn("Resource Exception: {}", ex.getMessage());
        return ResponseEntity.ok(new Response(
                HttpStatus.NOT_FOUND.value(),
                "Not found the object",
                ex.getMessage()
        ));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception ex) {
        log.error("Resource thrown an error: {}", ex.getMessage());
        return ResponseEntity.ok(new Response(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "The resource thrown an error",
                ex.getMessage()
        ));
    }
}
