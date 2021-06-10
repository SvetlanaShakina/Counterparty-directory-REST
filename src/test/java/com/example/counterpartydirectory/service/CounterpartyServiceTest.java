package com.example.counterpartydirectory.service;

import com.example.counterpartydirectory.dto.request.CounterpartyDtoRequest;
import com.example.counterpartydirectory.dto.request.CounterpartyUpdateDtoRequest;
import com.example.counterpartydirectory.dto.response.CounterpartyDtoResponse;
import com.example.counterpartydirectory.dto.response.SuccessResponse;
import com.example.counterpartydirectory.entity.Counterparty;
import com.example.counterpartydirectory.repository.CounterpartyRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CounterpartyServiceTest {

    @Autowired
    private CounterpartyServiceImpl service;

    @MockBean
    private CounterpartyRepository repository;

    @Autowired
    ModelMapper mapper;

    @Test
    public void testSave() {
        Counterparty counterparty = Counterparty.builder()
                .name("СКБ").inn("5504097128").kpp("550401001").bic("045209673").accountNumber("40702810045370100747").build();
        when(repository.save(counterparty)).thenReturn(counterparty);
        assertEquals(service.save(new CounterpartyDtoRequest("СКБ",
                        "5504097128", "550401001", "045209673", "40702810045370100747")),
                mapper.map(counterparty, CounterpartyDtoResponse.class));
    }

    @Test
    public void testDelete() {
        Counterparty counterparty = Counterparty.builder()
                .id(1).name("СКБ").inn("5504097128").kpp("550401001").bic("045209673").accountNumber("40702810045370100747").build();
        when(repository.findById(1)).thenReturn(Optional.of(counterparty));
        assertEquals(service.delete(1), new SuccessResponse("Контрагент с id 1 успешно удален"));
    }

    @Test
    public void testUpdate() {
        Counterparty counterparty = Counterparty.builder()
                .id(1).name("СКБ").inn("5504097128").kpp("550401001").bic("045209673").accountNumber("40702810045370100747").build();
        when(repository.findById(1)).thenReturn(Optional.of(counterparty));
        counterparty.setKpp("111222333");
        when(repository.save(counterparty)).thenReturn(counterparty);
        assertEquals(service.update(new CounterpartyUpdateDtoRequest(1, "СКБ",
                        "5504097128", "111222333", "045209673", "40702810045370100747")),
                mapper.map(counterparty, CounterpartyDtoResponse.class));
    }

    @Test
    public void testGetById() {
        Counterparty counterparty = Counterparty.builder().id(1).build();
        when(repository.findById(1)).thenReturn(Optional.of(counterparty));
        assertEquals(service.getById(1), mapper.map(counterparty, CounterpartyDtoResponse.class));
    }

    @Test
    public void testGetByName() {
        Counterparty counterparty = Counterparty.builder().name("Сбер").build();
        when(repository.findByName("Сбер")).thenReturn(Optional.of(counterparty));
        assertEquals(service.getByName("Сбер"), mapper.map(counterparty, CounterpartyDtoResponse.class));
    }

    @Test
    public void testGetByBicAndAccountNumber() {
        Counterparty counterparty = Counterparty.builder().bic("045209673").accountNumber("40702810045370100747").build();
        when(repository.findByBicAndAccountNumber("045209673", "40702810045370100747"))
                .thenReturn(Optional.of(counterparty));
        assertEquals(service.getByBicAndAccountNumber("045209673", "40702810045370100747"),
                mapper.map(counterparty, CounterpartyDtoResponse.class));
    }
}