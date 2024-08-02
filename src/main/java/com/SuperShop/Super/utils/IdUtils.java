package com.SuperShop.Super.utils;

import java.security.SecureRandom;
import java.util.Random;

public class IdUtils {
    private static final Random RANDOM = new SecureRandom();

    public static String generateId(char entityType) {
        if (entityType != 'A' && entityType != 'F' && entityType != 'P' && entityType != 'V') {
            throw new IllegalArgumentException("Tipo de entidade inválido: " + entityType);
        }
        int number = RANDOM.nextInt(9000) + 1000;
        return String.format("%c%d", entityType, number);
    }
}
