package com.project.AuthenticationService.repository;

import com.project.AuthenticationService.model.Account;
import com.project.AuthenticationService.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllByCustomer(Customer customer);
}
