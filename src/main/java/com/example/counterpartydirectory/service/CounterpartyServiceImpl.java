package com.example.counterpartydirectory.service;

import com.example.counterpartydirectory.dto.request.CounterpartyDtoRequest;
import com.example.counterpartydirectory.dto.request.CounterpartyUpdateDtoRequest;
import com.example.counterpartydirectory.dto.response.CounterpartyDtoResponse;
import com.example.counterpartydirectory.dto.response.SuccessResponse;
import com.example.counterpartydirectory.entity.Counterparty;
import com.example.counterpartydirectory.exception.CounterpartyException;
import com.example.counterpartydirectory.repository.CounterpartyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CounterpartyServiceImpl implements CounterpartyService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    CounterpartyRepository repository;

    @Override
    public CounterpartyDtoResponse save(CounterpartyDtoRequest counterpartyDtoRequest) {
        Counterparty counterparty = mapper.map(counterpartyDtoRequest, Counterparty.class);
        return mapper.map(repository.save(counterparty), CounterpartyDtoResponse.class);
    }

    @Override
    public CounterpartyDtoResponse update(CounterpartyUpdateDtoRequest counterpartyUpdateDtoRequest) {
        repository.findById(counterpartyUpdateDtoRequest.getId())
                .orElseThrow(() -> new CounterpartyException("Контрагент не найден"));
        Counterparty counterpartyToUpdate = mapper.map(counterpartyUpdateDtoRequest, Counterparty.class);
        return mapper.map(repository.save(counterpartyToUpdate), CounterpartyDtoResponse.class);
    }

    @Override
    public SuccessResponse delete(int id) {
        repository.findById(id)
                .orElseThrow(() -> new CounterpartyException("Контрагент с id " + id + " не найден"));
        repository.deleteById(id);
        return new SuccessResponse("Контрагент с id " + id + " успешно удален");
    }

    @Override
    public SuccessResponse delete(String name) {
        repository.findByName(name)
                .orElseThrow(() -> new CounterpartyException("Контрагент с наименованием " + name + " не найден"));
        repository.deleteByName(name);
        return new SuccessResponse("Контрагент с наименованием " + name + " успешно удален");
    }

    @Override
    public CounterpartyDtoResponse getById(int id) {
        return mapper.map(repository.findById(id)
                        .orElseThrow(() -> new CounterpartyException("Контрагент с id " + id + " не найден")),
                CounterpartyDtoResponse.class);
    }

    @Override
    public CounterpartyDtoResponse getByName(String name) {
        return mapper.map(repository.findByName(name)
                        .orElseThrow(() -> new CounterpartyException("Контрагент с наименованием " + name + " не найден")),
                CounterpartyDtoResponse.class);
    }

    @Override
    public CounterpartyDtoResponse getByBicAndAccountNumber(String bic, String accountNumber) {
        return mapper.map(repository.findByBicAndAccountNumber(bic, accountNumber)
                        .orElseThrow(() -> new CounterpartyException("Контрагент с бик " + bic + " и номером счета " + accountNumber + " не найден")),
                CounterpartyDtoResponse.class);
    }
}