package com.SuperShop.Super.dto;

import lombok.Data;
@Data
public class ProdutoDTO {
    private String adminId;
    private String nome;
    private String descricao;
    private String preco;
    private String quantidade;
    private String fornecedorId;
    public ProdutoDTO(String nome, String descricao, String preco, String quantidade, String fornecedorId, String adminId) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.fornecedorId = fornecedorId;
        this.adminId = adminId;
    }
}