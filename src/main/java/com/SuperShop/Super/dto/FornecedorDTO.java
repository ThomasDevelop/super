package com.SuperShop.Super.dto;

import lombok.Data;
@Data
public class FornecedorDTO {
    private String id;
    private String nome;
    private String cnpj;
    private String email;
    private String senha;
    private String salt;
    private String adminId;
}