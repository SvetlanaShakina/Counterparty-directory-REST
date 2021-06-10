package com.example.counterpartydirectory.exception;

/**
 * Исключение, выбрасываемое, если в запросе указаны некорректные данные
 */
public class CounterpartyException extends RuntimeException {
    public CounterpartyException(String message) {
        super(message);
    }
}