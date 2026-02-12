package com.project.AccountService.service;

import com.project.AccountService.dto.TransactionDTO;
import com.project.AccountService.model.Transaction;
import com.project.AccountService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CustomerService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Transactional
    public ResponseEntity<?> makeTransaction(TransactionDTO transactionDTO)
    {
        Transaction transaction=new Transaction();
        transaction.setSenderId(transactionDTO.getSenderId());
        transaction.setReceiverId(transactionDTO.getReceiverId());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setLocalDateTime(LocalDateTime.now());
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Transaction Successful!");
    }
    public Double getBalance()
    {
        return 0D;
    }
}
