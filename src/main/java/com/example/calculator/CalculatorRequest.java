package com.example.calculator;

public class CalculatorRequest {
    private double a;
    private double b;
    private String operation;

    public CalculatorRequest() {}

    public CalculatorRequest(double a, double b, String operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    public double getA() { return a; }
    public void setA(double a) { this.a = a; }

    public double getB() { return b; }
    public void setB(double b) { this.b = b; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
}