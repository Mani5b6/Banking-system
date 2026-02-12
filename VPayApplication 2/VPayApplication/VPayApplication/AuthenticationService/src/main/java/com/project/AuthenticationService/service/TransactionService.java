package com.project.AuthenticationService.service;

import com.project.AuthenticationService.dto.TransactionDTO;
import com.project.AuthenticationService.model.Account;
import com.project.AuthenticationService.model.Customer;
import com.project.AuthenticationService.repository.AccountRepository;
import com.project.AuthenticationService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Transactional
    public int makeTransaction(TransactionDTO transactionDTO)
    {
        Account senderAccount=accountRepository.findById(transactionDTO.getSenderAccountNumber()).orElse(null);
        Account receiverAccount=accountRepository.findById(transactionDTO.getReceiverAccountNumber()).orElse(null);
        if (senderAccount!=null && receiverAccount!=null)
        {
            if(senderAccount.getBalance()>=transactionDTO.getAmount())
            {
                senderAccount.setBalance(senderAccount.getBalance()-transactionDTO.getAmount());
                receiverAccount.setBalance(receiverAccount.getBalance()+transactionDTO.getAmount());
                accountRepository.save(senderAccount);
                accountRepository.save(receiverAccount);
                return 0;
            }
            else if(senderAccount.getStatus().equals("Frozen") || receiverAccount.getStatus().equals("Frozen"))
            {
                return 2;
            }
            else
            {
                return 3;
            }
        }
        else
        {
            return 1;
        }
    }
    public Account getAccountById(Long id)
    {
        return accountRepository.findById(id).orElse(null);
    }
    public List<Account> getAccountsByCustomerId(Long id)
    {
        Customer customer=customerRepository.findById(id).orElse(null);
        return accountRepository.findAllByCustomer(customer);
    }
    public String getAccountBalanceByAccountNumber(Long id)
    {
        Account account=accountRepository.findById(id).orElse(null);
        if (account!=null)
        {
            return account.getBalance().toString();
        }
        else
        {
            return "No such account with id : "+id;
        }
    }
}
