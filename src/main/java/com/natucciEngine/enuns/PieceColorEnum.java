package com.natucciEngine.enuns;

public enum PieceColorEnum {
    WHITE(1, "WHITE"),
    BLACK(2, "BLACK");

    private int colorCode;
    private String color;

    PieceColorEnum(int colorCode, String color) {
        this.color = color;
        this.colorCode = colorCode;
    }

    public String getColor() {
        return color;
    }

    public int getColorCode() {
        return colorCode;
    }

}
