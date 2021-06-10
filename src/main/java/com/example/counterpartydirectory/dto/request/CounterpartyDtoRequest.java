package com.example.counterpartydirectory.dto.request;

import com.example.counterpartydirectory.validation.AccountNumberValid;
import com.example.counterpartydirectory.validation.BicValid;
import com.example.counterpartydirectory.validation.InnValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@AccountNumberValid.List({
        @AccountNumberValid(bic = "bic", accountNumber = "accountNumber")
})
public class CounterpartyDtoRequest {

    /**
     * Наименование контрагента
     */
    @Pattern(regexp = "^[^\\s].*$", message = "Некорректное наименование")
    @Size(min = 1, max = 20, message = "Наименование должно содержать 1-20 символов")
    private String name;

    /**
     * ИНН контрагента
     */
    @Size(min = 10, max = 12, message = "ИНН должен состоять из 10 или 12 цифр")
    @InnValid
    private String inn;

    /**
     * КПП контрагента
     */
    @Size(min = 9, max = 9, message = "КПП должен состоять из 9 цифр")
    private String kpp;

    /**
     * БИК контрагента
     */
    @BicValid
    @Size(min = 9, max = 9, message = "БИК должен состоять из 9 цифр")
    private String bic;

    /**
     * Номер счета контрагента
     */
    @Size(min = 20, max = 20, message = "Номер счета должен состоять из 20 цифр")
    private String accountNumber;
}