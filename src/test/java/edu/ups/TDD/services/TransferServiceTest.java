package edu.ups.TDD.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransferServiceTest {

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
        assertEquals(500.0, result.getRemainingBalance());
        assertEquals("Fondos insuficientes para realizar la transferencia", result.getMessage());
    }
}
