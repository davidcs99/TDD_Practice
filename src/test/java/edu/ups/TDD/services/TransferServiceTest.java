package edu.ups.TDD.services;

import edu.ups.TDD.model.TransferRequest;
import edu.ups.TDD.model.TransferResult;
import edu.ups.TDD.service.TransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferServiceTest {
    private static final String ACCOUNT_NUMBER = "123456789";
    private static final String SUCCESSFUL_TRANSFER_MESSAGE = "Transferencia exitosa";
    private static final String INSUFFICIENT_FUNDS_MESSAGE = "Fondos insuficientes para realizar la transferencia";
    private static final double INITIAL_AMOUNT = 500.0;
    private static final double EXCESS_AMOUNT = 700.0;

    @Autowired
    private TransferService transferService;

    @Nested
    @DisplayName("Execute Transfer Tests")
    class ExecuteTransferTests {

        @Test
        @DisplayName("Should complete transfer when account has sufficient funds")
        void shouldCompleteTransferWhenSufficientFunds() {
            // Given
            TransferRequest request = buildTransferRequest(INITIAL_AMOUNT);

            // When
            TransferResult result = transferService.executeTransfer(request);

            // Then
            assertAll(
                    () -> assertTrue(result.isSuccess(), "Transfer should be successful"),
                    () -> assertEquals(INITIAL_AMOUNT, result.getRemainingBalance(), "Remaining balance should match"),
                    () -> assertEquals(SUCCESSFUL_TRANSFER_MESSAGE, result.getMessage(), "Success message should match")
            );
        }

        @Test
        @DisplayName("Should reject transfer when account has insufficient funds")
        void shouldRejectTransferWhenInsufficientFunds() {
            // Given
            setupInitialBalance();
            TransferRequest request = buildTransferRequest(EXCESS_AMOUNT);

            // When
            TransferResult result = transferService.executeTransfer(request);

            // Then
            assertAll(
                    () -> assertFalse(result.isSuccess(), "Transfer should fail"),
                    () -> assertEquals(INITIAL_AMOUNT, result.getRemainingBalance(), "Balance should remain unchanged"),
                    () -> assertEquals(INSUFFICIENT_FUNDS_MESSAGE, result.getMessage(), "Error message should match")
            );
        }

        private TransferRequest buildTransferRequest(double amount) {
            return new TransferRequest(ACCOUNT_NUMBER, amount, "Test transfer");
        }

        private void setupInitialBalance() {
            transferService.executeTransfer(buildTransferRequest(INITIAL_AMOUNT));
        }
    }
}