package com.SuperShop.Super.dto;

import lombok.Data;
@Data public class AdministradorDTO {
    private String id;
    private String nome;
    private String senha;
    private String email;
    private String salt;
}