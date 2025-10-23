package com.group.libraryapp.dto.calculator.request;

public class CalculatorAddRequest {
    private final int a;
    private final int b;

    public CalculatorAddRequest(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
