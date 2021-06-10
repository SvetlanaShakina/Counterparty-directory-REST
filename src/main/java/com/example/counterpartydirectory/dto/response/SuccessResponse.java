package com.example.counterpartydirectory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ответ при успешном выполении запроса
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessResponse {
    private String message;
}
