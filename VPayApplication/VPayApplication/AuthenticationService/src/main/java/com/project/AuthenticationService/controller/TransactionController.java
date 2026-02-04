package com.project.AuthenticationService.controller;

import com.project.AuthenticationService.dto.TransactionDTO;
import com.project.AuthenticationService.model.Account;
import com.project.AuthenticationService.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/makeTransaction")
    public int makeTransaction(@RequestBody @Valid TransactionDTO transactionDTO)
    {
        return transactionService.makeTransaction(transactionDTO);
    }
    @GetMapping("getAccountById/{id}")
    private Account getAccountById(@PathVariable Long id)
    {
        return transactionService.getAccountById(id);
    }
    @GetMapping("/getAccountsByCustomerId/{id}")
    public List<Account> getAccountsByCustomerId(@PathVariable Long id)
    {
        return transactionService.getAccountsByCustomerId(id);
    }
    @GetMapping("getAccountBalance/{id}")
    public String getAccountBalanceByAccountNumber(@PathVariable Long id)
    {
        return transactionService.getAccountBalanceByAccountNumber(id);
    }
}
