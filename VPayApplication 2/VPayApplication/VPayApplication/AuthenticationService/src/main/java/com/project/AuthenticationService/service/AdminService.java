package com.project.AuthenticationService.service;

import com.project.AuthenticationService.model.Account;
import com.project.AuthenticationService.model.Customer;
import com.project.AuthenticationService.repository.AccountRepository;
import com.project.AuthenticationService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public Account getAccountById(Long id)
    {
        return accountRepository.findById(id).orElse(null);
    }
    public List<Account> getAccountsByCustomerId(Long id)
    {
        Customer customer=customerRepository.findById(id).orElse(null);
        return accountRepository.findAllByCustomer(customer);
    }
    @Transactional
    public boolean freezeAccountByAccountNumber(Long id)
    {
        Account account=accountRepository.findById(id).orElse(null);
        if (account==null)
            return false;
        account.setStatus("Frozen");
        accountRepository.save(account);
        return true;
    }
    @Transactional
    public boolean unFreezeAccountByAccountNumber(Long id)
    {
        Account account=accountRepository.findById(id).orElse(null);
        if (account==null)
            return false;
        account.setStatus("Active");
        accountRepository.save(account);
        return true;
    }
}
