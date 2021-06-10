package com.example.counterpartydirectory.service;

import com.example.counterpartydirectory.dto.request.CounterpartyDtoRequest;
import com.example.counterpartydirectory.dto.request.CounterpartyUpdateDtoRequest;
import com.example.counterpartydirectory.dto.response.CounterpartyDtoResponse;
import com.example.counterpartydirectory.dto.response.SuccessResponse;

public interface CounterpartyService {
    /**
     * Поиск контрагента по id
     * @param id - идентификатор контрагента
     * @return - контрагент с указанным идентификатором
     */
    CounterpartyDtoResponse getById(int id);

    /**
     * Поиск контрагента по наименованию
     * @param name - наименование контрагента
     * @return - контрагент с указанным наименованием
     */
    CounterpartyDtoResponse getByName(String name);

    /**
     * Поиск контрагента по БИК и номеру счета
     * @param bic - БИК
     * @param accountNumber - номер счета
     * @return - контрагент с указанными БИК и номером счета
     */
    CounterpartyDtoResponse getByBicAndAccountNumber(String bic, String accountNumber);

    /**
     * Добавление контрагента
     * @param counterparty - DTO контрагента
     * @return - добавленный контрагент
     */
    CounterpartyDtoResponse save(CounterpartyDtoRequest counterparty);

    /**
     * Обновление контрагента
     * @param counterparty - контрагент
     * @return - обновленный контрагент
     */
    CounterpartyDtoResponse update(CounterpartyUpdateDtoRequest counterparty);

    /**
     * Удаление контрагента по id
     * @param id - идентификатор контрагента
     * @return - успешный ответ
     */
    SuccessResponse delete(int id);

    /**
     * Удаление контрагента по наименованию
     * @param name - наименование контрагента
     * @return - успешный ответ
     */
    SuccessResponse delete(String name);
}
