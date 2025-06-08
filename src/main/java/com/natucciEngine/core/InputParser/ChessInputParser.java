package com.natucciEngine.core.InputParser;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChessInputParser {
    private static final Logger logger = LoggerFactory.getLogger(ChessInputParser.class);

    /**
     * Converte um input "e2 e4" em um objeto Move.
     *
     * @param input string com duas casas de xadrez separadas por espaço
     * @return Move contendo as coordenadas no array 8×8
     * @throws IllegalArgumentException se o formato for inválido
     */
    public static Move parseMove(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input não pode ser nulo");
        }

        String[] parts = input.trim().toLowerCase().split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Formato inválido. Use algo como \"e2 e4\".");
        }

        int[] from = parseSquare(parts[0]);
        int[] to = parseSquare(parts[1]);

        return new Move(from[0], from[1], to[0], to[1]);
    }

    /**
     * Converte uma casa de xadrez ("a1"–"h8") em [row, col].
     * Mapeamento:
     * col = fileChar - 'a' → 0..7
     * row = '8' - rankChar → 0..7
     *
     * @param sq string de 2 caracteres, primeiro file (letra), depois rank (número)
     * @return array {row, col}
     */
    private static int[] parseSquare(String sq) {
        if (sq.length() != 2) {
            throw new IllegalArgumentException(
                    "Casa deve ter 2 caracteres: letra+número, ex: a1, h8.");
        }
        char file = sq.charAt(0);
        char rank = sq.charAt(1);

        if (file < 'a' || file > 'h' || rank < '1' || rank > '8') {
            throw new IllegalArgumentException(
                    "Casa inválida: " + sq + ". Use letras a–h e números 1–8.");
        }

        int col = file - 'a';
        int row = '8' - rank; // row=0 para rank8, row=7 para rank1
        return new int[] { row, col };
    }

    public static Move main() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite seu movimento: \n");
        String line = sc.nextLine();
        // sc.close();

        try {
            Move move = parseMove(line);
            return move;
        } catch (IllegalArgumentException e) {
            logger.error("Erro: ao parse de movimento do jogador:  " + e.getMessage());
        }
		return null;
    }
}
