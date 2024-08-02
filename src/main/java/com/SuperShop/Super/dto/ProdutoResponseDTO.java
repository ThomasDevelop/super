package com.SuperShop.Super.dto;

import lombok.Data;

@Data
public class ProdutoResponseDTO {
    private String adminId;
    private String nome;
    private String descricao;
    private String preco;
    private String quantidade;

    public ProdutoResponseDTO(String adminId, String nome, String descricao, String preco, String quantidade) {
        this.adminId = adminId;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}