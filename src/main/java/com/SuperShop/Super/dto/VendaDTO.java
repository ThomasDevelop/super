package com.SuperShop.Super.dto;

import lombok.Data;

@Data
public class VendaDTO {
    private String produto;
    private int quantidade;
    private double valorTotal;
}