package com.SuperShop.Super.utils;

public class ProdutoUtils {
    public static boolean isPrecoValido(double preco) {
        return preco > 0.10;
    }
    public static boolean isQuantidadeValida(int quantidade) {
        return quantidade >= 1;
    }
}
