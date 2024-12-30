package edu.ups.TDD.services;

import edu.ups.TDD.model.TransferRequest;
import edu.ups.TDD.model.TransferResult;
import edu.ups.TDD.service.TransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransferServiceTest {
    @Autowired
    private TransferService transferService;


    @Test
    @DisplayName("Should Transfer Successfully When Enough Funds")
    public void shouldTransferSuccessfullyWhenEnoughFunds() {
        // Given
        TransferRequest request = new TransferRequest("123456789", 500.0, "Test transfer");

        // When
        TransferResult result = transferService.executeTransfer(request);

        // Then
        assertTrue(result.isSuccess());
        assertEquals(500.0, result.getRemainingBalance());
        assertEquals("Transferencia exitosa", result.getMessage());
    }

    @Test
    @DisplayName("Should Fail Transfer When Insufficient Funds")
    public void shouldFailTransferWhenInsufficientFunds() {
        // Given
        transferService.executeTransfer(new TransferRequest("123456789", 500.0, "Initial transfer"));
        TransferRequest request = new TransferRequest("123456789", 700.0, "Test transfer");

        // When
        TransferResult result = transferService.executeTransfer(request);

        // Then
        assertFalse(result.isSuccess());
        assertEquals(0.0, result.getRemainingBalance());
        assertEquals("Fondos insuficientes para realizar la transferencia", result.getMessage());
    }
}
