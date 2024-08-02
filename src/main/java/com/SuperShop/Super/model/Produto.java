package com.SuperShop.Super.model;

import lombok.Data;
@Data
public class Produto {
    private String id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private String fornecedorId;
    private String adminId;
}