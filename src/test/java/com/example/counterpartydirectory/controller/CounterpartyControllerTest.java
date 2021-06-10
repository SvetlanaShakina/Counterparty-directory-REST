package com.example.counterpartydirectory.controller;

import com.example.counterpartydirectory.dto.request.CounterpartyDtoRequest;
import com.example.counterpartydirectory.dto.request.CounterpartyUpdateDtoRequest;
import com.example.counterpartydirectory.dto.response.CounterpartyDtoResponse;
import com.example.counterpartydirectory.dto.response.SuccessResponse;
import com.example.counterpartydirectory.service.CounterpartyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CounterpartyController.class)
public class CounterpartyControllerTest {

    @MockBean
    private CounterpartyService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testAddCounterparty() throws Exception {
        CounterpartyDtoRequest counterpartyRequest = new CounterpartyDtoRequest("СКБ",
                "5504097128", "550401001", "045209673", "40702810045370100747");
        CounterpartyDtoResponse counterpartyResponse = new CounterpartyDtoResponse(1, "СКБ",
                "5504097128", "550401001", "045209673", "40702810045370100747");
        when(service.save(counterpartyRequest)).thenReturn(counterpartyResponse);
        mvc.perform(post("/counterparties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(counterpartyRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("СКБ")))
                .andExpect(jsonPath("$.inn", is("5504097128")))
                .andExpect(jsonPath("$.kpp", is("550401001")))
                .andExpect(jsonPath("$.bic", is("045209673")))
                .andExpect(jsonPath("$.accountNumber", is("40702810045370100747")));
        verify(service, times(1)).save(counterpartyRequest);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testUpdateCounterparty() throws Exception {
        CounterpartyUpdateDtoRequest counterpartyRequest = new CounterpartyUpdateDtoRequest(1, "СКБ",
                "5504097128", "550401001", "045209673", "40702810045370100747");
        CounterpartyDtoResponse counterpartyResponse = new CounterpartyDtoResponse(1, "Феникс",
                "5504097128", "550401001", "045209673", "40702810045370100747");
        when(service.update(counterpartyRequest)).thenReturn(counterpartyResponse);
        mvc.perform(put("/counterparties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(counterpartyRequest)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Феникс")))
                .andExpect(jsonPath("$.inn", is("5504097128")))
                .andExpect(jsonPath("$.kpp", is("550401001")))
                .andExpect(jsonPath("$.bic", is("045209673")))
                .andExpect(jsonPath("$.accountNumber", is("40702810045370100747")));
        verify(service, times(1)).update(counterpartyRequest);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDeleteCounterpartyByName() throws Exception {
        SuccessResponse response = new SuccessResponse();
        when(service.delete("Abc")).thenReturn(response);
        mvc.perform(delete("/counterparties/?name=Abc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service, times(1)).delete("Abc");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDeleteCounterpartyById() throws Exception {
        SuccessResponse response = new SuccessResponse();
        when(service.delete(1)).thenReturn(response);
        mvc.perform(delete("/counterparties/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service, times(1)).delete(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testGetByBicAndAccountNumber() throws Exception {
        CounterpartyDtoResponse counterparty = new CounterpartyDtoResponse();
        String bic = "045209673";
        String accountNumber = "40702810045370100747";
        counterparty.setBic(bic);
        counterparty.setAccountNumber(accountNumber);
        when(service.getByBicAndAccountNumber(bic, accountNumber)).thenReturn(counterparty);
        mvc.perform(get("/counterparties/?bic=045209673&accountNumber=40702810045370100747"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bic", is(bic)))
                .andExpect(jsonPath("$.accountNumber", is(accountNumber)));
        verify(service, times(1)).getByBicAndAccountNumber(bic, accountNumber);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testGetByName() throws Exception {
        CounterpartyDtoResponse counterparty = new CounterpartyDtoResponse();
        String name = "СКБ";
        counterparty.setName(name);
        when(service.getByName(name)).thenReturn(counterparty);
        mvc.perform(get("/counterparties/{name}", name))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)));
        verify(service, times(1)).getByName(name);
        verifyNoMoreInteractions(service);
    }
}
