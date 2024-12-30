package edu.ups.TDD.service;

import edu.ups.TDD.exception.InsufficientFundsException;
import edu.ups.TDD.model.Account;
import edu.ups.TDD.model.TransferRequest;
import edu.ups.TDD.model.TransferResult;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final Account account;

    public TransferService() {
        this.account = new Account(1000.0);
    }

    public TransferResult executeTransfer(TransferRequest request) {
        try {
            account.debit(request.getAmount());
            return new TransferResult(true, "Transferencia exitosa", account.getBalance());
        } catch (InsufficientFundsException e) {
            return new TransferResult(false, e.getMessage(), account.getBalance());
        }
    }
}
