package com.SuperShop.Super.model;

import lombok.Data;

@Data
public class Venda {
    private String id;
    private String produto;
    private int quantidade;
    private double valorTotal;
}