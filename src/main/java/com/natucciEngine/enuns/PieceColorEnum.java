package com.natucciEngine.enuns;

import lombok.Getter;

@Getter
public enum PieceColorEnum {
    WHITE(0, "W"),
    BLACK(1, "B");

    private int colorCode;
    private String color;

    PieceColorEnum(int colorCode, String color) {
        this.color = color;
        this.colorCode = colorCode;
    }
}
