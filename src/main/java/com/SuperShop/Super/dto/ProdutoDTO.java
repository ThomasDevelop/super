package com.SuperShop.Super.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoDTO {
    private String adminId;
    private String nome;
    private String descricao;
    private String preco;
    private String quantidade;
    private String fornecedorId;

}