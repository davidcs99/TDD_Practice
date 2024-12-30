package edu.ups.TDD.model;

public class TransferResult {
    private boolean success;
    private String message;
    private double remainingBalance;

    public TransferResult(boolean success, String message, double remainingBalance) {
        this.success = success;
        this.message = message;
        this.remainingBalance = remainingBalance;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public double getRemainingBalance() { return remainingBalance; }
}