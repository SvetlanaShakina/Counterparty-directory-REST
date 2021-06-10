package com.example.counterpartydirectory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ответ при успешном выполении запроса с деталями совершенной операции
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CounterpartyDtoResponse {
    private int id;
    private String name;
    private String inn;
    private String kpp;
    private String bic;
    private String accountNumber;
}