package com.example.counterpartydirectory.exception;

import com.example.counterpartydirectory.dto.response.BadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Обработчик исключений
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработка исключения, если не удалось найти контрагента в БД
     * @param exception - CounterpartyException
     * @return - BadResponse
     */
    @ExceptionHandler(CounterpartyException.class)
    public ResponseEntity<BadResponse> handleCounterpartyException(CounterpartyException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BadResponse(exception.getMessage()));
    }

    /**
     * Обработка исключения, если в запросе указаны некорректные данные
     * @param exception - MethodArgumentNotValidException
     * @return - ответ с перечислением ошибок валидации
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleCustomValidationError(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработка исключения, если контрагент с указанным наименованием уже есть в базе
     * @param exception - SQLIntegrityConstraintViolationException
     * @return - BadResponse
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<BadResponse> handleException(SQLIntegrityConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BadResponse("Контрагент с таким наименованием уже есть в справочнике"));
    }
}