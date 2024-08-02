package com.SuperShop.Super.model;

import lombok.Data;
@Data public class Administrador {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String salt;
}