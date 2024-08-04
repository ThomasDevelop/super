package com.SuperShop.Super.dto;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
=======
import lombok.Data;
@Data
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
public class ProdutoDTO {
    private String adminId;
    private String nome;
    private String descricao;
    private String preco;
    private String quantidade;
    private String fornecedorId;
<<<<<<< HEAD
=======
    public ProdutoDTO(String nome, String descricao, String preco, String quantidade, String fornecedorId, String adminId) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.fornecedorId = fornecedorId;
        this.adminId = adminId;
    }
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
}