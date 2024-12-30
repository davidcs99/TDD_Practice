package edu.ups.TDD.model;

public class TransferRequest {
    private String destinationAccount;
    private double amount;
    private String description;

    public TransferRequest(String destinationAccount, double amount, String description) {
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.description = description;
    }

    // Getters
    public String getDestinationAccount() { return destinationAccount; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
}
