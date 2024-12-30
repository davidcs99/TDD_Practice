package edu.ups.TDD.model;

import edu.ups.TDD.exception.InsufficientFundsException;

public class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void debit(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Fondos insuficientes para realizar la transferencia");
        }
        balance -= amount;
    }
}

