package com.natucciEngine.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {
    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * Converte um número de 1 a 26 na letra minúscula correspondente.
     *
     * @param number o número a converter (1 = 'a', 2 = 'b', ..., 26 = 'z')
     * @return o caractere correspondente
     * @throws IllegalArgumentException se number < 1 ou number > 26
     */
    public static char converteNumeroPaLetra(int number) {
        if (number < 1 || number > 26) {
            logger.error("Numero invalido: deve ser entre 1 e 26.");
            throw new IllegalArgumentException(
                    "Numero invalido: deve ser entre 1 e 26.");
        }
        return (char) ('a' + number - 1);
    }
}
