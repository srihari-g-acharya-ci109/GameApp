package com.gameclub.gameclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gameclub.gameclub.dto.TransactionDto;
import com.gameclub.gameclub.model.TransactionRecord;
import com.gameclub.gameclub.services.TransactionService;

@RestController
@CrossOrigin("*")
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/save")
    public TransactionRecord saveTransaction(@RequestBody TransactionDto dto) {
        return transactionService.create(dto);
    }

    @GetMapping("/all")
    public List<TransactionRecord> viewAllTransactions() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public TransactionRecord getTransactionById(@PathVariable String id) {
        return transactionService.findById(id);
    }

    @PutMapping("/{id}")
    public TransactionRecord updateTransaction(@PathVariable String id, @RequestBody TransactionDto dto) {
        return transactionService.update(id, dto);
    }
}
