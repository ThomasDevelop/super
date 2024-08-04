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
public class ProdutoResponseDTO {
    private String adminId;
    private String nome;
    private String descricao;
    private String preco;
    private String quantidade;

<<<<<<< HEAD
=======
    public ProdutoResponseDTO(String adminId, String nome, String descricao, String preco, String quantidade) {
        this.adminId = adminId;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
}