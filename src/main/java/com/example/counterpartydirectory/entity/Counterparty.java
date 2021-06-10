package com.example.counterpartydirectory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Counterparty {

    /**
     * Идентификатор контрагента
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Наименование контрагента
     */
    private String name;

    /**
     * ИНН контрагента
     */
    private String inn;

    /**
     * КПП контрагента
     */
    private String kpp;

    /**
     * БИК контагента
     */
    private String bic;

    /**
     * Номер счета контрагента
     */
    private String accountNumber;
}