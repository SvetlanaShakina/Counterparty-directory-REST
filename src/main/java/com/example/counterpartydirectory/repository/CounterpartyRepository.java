package com.example.counterpartydirectory.repository;

import com.example.counterpartydirectory.entity.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, Integer> {

    /**
     * Удаление контрагента по наименованию
     * @param name - наименование контрагента
     */
    void deleteByName(String name);

    /**
     * Поиск контрагента по наименованию
     * @param name - наименование контрагента
     * @return - контрагент
     */
    Optional<Counterparty> findByName(String name);

    /**
     * Поиск контрагента по БИК и номеру счета
     * @param bic - БИК
     * @param accountNumber - номер счета
     * @return - контрагент
     */
    Optional<Counterparty> findByBicAndAccountNumber(String bic, String accountNumber);
}
