package com.project.AuthenticationService.controller;

import com.project.AuthenticationService.model.Account;
import com.project.AuthenticationService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("getAccountById/{id}")
    private Account getAccountById(@PathVariable Long id)
    {
        return adminService.getAccountById(id);
    }
    @GetMapping("/getAccountsByCustomerId/{id}")
    public List<Account> getAccountsByCustomerId(@PathVariable Long id)
    {
        return adminService.getAccountsByCustomerId(id);
    }
    @PutMapping("/freezeAccount/{id}")
    public boolean freezeAccountByAccountNumber(@PathVariable Long id)
    {
        return adminService.freezeAccountByAccountNumber(id);
    }
    @PutMapping("/unFreezeAccount/{id}")
    public boolean unFreezeAccountByAccountNumber(@PathVariable Long id)
    {
        return adminService.unFreezeAccountByAccountNumber(id);
    }
}
