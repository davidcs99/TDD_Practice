package edu.ups.TDD.controller;

import edu.ups.TDD.model.TransferRequest;
import edu.ups.TDD.model.TransferResult;
import edu.ups.TDD.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<TransferResult> executeTransfer(@RequestBody TransferRequest request) {
        TransferResult result = transferService.executeTransfer(request);
        return ResponseEntity.ok(result);
    }
}