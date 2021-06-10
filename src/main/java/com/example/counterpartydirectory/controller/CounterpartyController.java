package com.example.counterpartydirectory.controller;

import com.example.counterpartydirectory.dto.request.CounterpartyDtoRequest;
import com.example.counterpartydirectory.dto.request.CounterpartyUpdateDtoRequest;
import com.example.counterpartydirectory.dto.response.CounterpartyDtoResponse;
import com.example.counterpartydirectory.dto.response.SuccessResponse;
import com.example.counterpartydirectory.service.CounterpartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/counterparties")
public class CounterpartyController {

    @Autowired
    CounterpartyService service;

    /**
     * Добавление контрагента
     * @param counterparty - DTO контрагента
     * @return - DTO добавленноого контрагента
     */
    @PostMapping()
    public CounterpartyDtoResponse addCounterparty(@RequestBody @Valid CounterpartyDtoRequest counterparty) {
        return service.save(counterparty);
    }

    /**
     * Обновление контрагента
     * @param counterparty - DTO контрагента
     * @return - DTO обновленного контрагента
     */
    @PutMapping()
    public CounterpartyDtoResponse updateCounterparty(@RequestBody CounterpartyUpdateDtoRequest counterparty) {
        return service.update(counterparty);
    }

    /**
     * Удаление контрагента по id
     * @param id - идентификатор контрагента
     * @return - успешный ответ
     */
    @DeleteMapping("/{id}")
    public SuccessResponse deleteCounterparty(@PathVariable int id) {
        return service.delete(id);
    }

    /**
     * Удаление контрагента по наименованию
     * @param name - наименование контрагента
     * @return - успешный ответ
     */
    @DeleteMapping()
    public SuccessResponse deleteCounterparty(@RequestParam String name) {
        return service.delete(name);
    }

    /**
     * Поиск контрагента по наименованию
     * @param name - наименование контрагента
     * @return - DTO контрагента
     */
    @GetMapping("/{name}")
    public CounterpartyDtoResponse getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    /**
     * Поиск контрагента по БИК и номеру счета
     * @param bic - БИК
     * @param accountNumber - номер счета
     * @return - DTO контрагента
     */
    @GetMapping()
    public CounterpartyDtoResponse getByBicAndAccountNumber(@RequestParam String bic, @RequestParam String accountNumber) {
        return service.getByBicAndAccountNumber(bic, accountNumber);
    }
}