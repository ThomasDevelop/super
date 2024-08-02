package com.SuperShop.Super.utils;

public class NomeUtils {
    public static void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty() || !nome.matches("^[A-Za-zÀ-ÿ ]+$")) {
            throw new IllegalArgumentException("Nome deve conter apenas letras: ");
        }
    }
}
