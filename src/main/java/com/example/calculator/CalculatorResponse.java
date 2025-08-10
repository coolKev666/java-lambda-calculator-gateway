package com.example.calculator;

public class CalculatorResponse {
    private double result;
    private String operation;
    private boolean success;
    private String error;

    public CalculatorResponse() {}

    public CalculatorResponse(double result, String operation) {
        this.result = result;
        this.operation = operation;
        this.success = true;
    }

    public CalculatorResponse(String error) {
        this.error = error;
        this.success = false;
    }

    public double getResult() { return result; }
    public void setResult(double result) { this.result = result; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}