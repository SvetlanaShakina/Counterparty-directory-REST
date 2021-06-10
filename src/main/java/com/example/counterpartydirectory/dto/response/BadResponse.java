package com.example.counterpartydirectory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Негативный ответ, если не удалось выполнить запрос
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadResponse {
    private String message;
}
