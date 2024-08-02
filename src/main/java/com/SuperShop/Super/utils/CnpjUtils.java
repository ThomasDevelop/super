package com.SuperShop.Super.utils;

public class CnpjUtils {
    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null) {
            return false;
        }

        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^\\d]", "");

        if (cnpj.length() != 14) {
            return false;
        }

        try {
            int[] cnpjArray = new int[cnpj.length()];
            for (int i = 0; i < cnpj.length(); i++) {
                cnpjArray[i] = Integer.parseInt(String.valueOf(cnpj.charAt(i)));
            }

            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int soma = 0;

            for (int i = 0; i < 12; i++) {
                soma += cnpjArray[i] * pesos1[i];
            }

            int resto = soma % 11;
            int dig1 = (resto < 2) ? 0 : 11 - resto;
            soma = 0;

            for (int i = 0; i < 12; i++) {
                soma += cnpjArray[i] * pesos2[i];
            }

            soma += dig1 * pesos2[12];
            resto = soma % 11;
            int dig2 = (resto < 2) ? 0 : 11 - resto;

            return cnpjArray[12] == dig1 && cnpjArray[13] == dig2;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
