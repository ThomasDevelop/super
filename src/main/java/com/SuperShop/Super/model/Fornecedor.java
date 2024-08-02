package com.SuperShop.Super.model;

import lombok.Data;
@Data public class Fornecedor {
    private String id;
    private String nome;
    private String cnpj;
    private String email;
    private String salt;
    private String senha;
    private String adminId;
}