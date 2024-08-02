package com.SuperShop.Super.model;

import lombok.Data;

@Data
public class ReciboVenda {
    private String vendaId;
    private String produtoNome;
    private int quantidade;
    private double valorTotal;
    private double precoUnitario;
    private double valorPago;
    private String mensagem;
}