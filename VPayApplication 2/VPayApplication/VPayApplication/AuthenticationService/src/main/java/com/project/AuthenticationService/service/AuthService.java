package com.project.AuthenticationService.service;

import com.project.AuthenticationService.dto.LoginRequest;
import com.project.AuthenticationService.dto.RegisterRequest;
import com.project.AuthenticationService.exception.EmailAlreadyExistsException;
import com.project.AuthenticationService.exception.UserNotFoundException;
import com.project.AuthenticationService.model.Admin;
import com.project.AuthenticationService.model.Customer;
import com.project.AuthenticationService.repository.AccountRepository;
import com.project.AuthenticationService.repository.AdminRepository;
import com.project.AuthenticationService.repository.CustomerRepository;
import com.project.AuthenticationService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Transactional
    public ResponseEntity<?> registerCustomer(RegisterRequest registerRequest) throws EmailAlreadyExistsException {
        boolean exists=customerRepository.findByEmailExists(registerRequest.getEmail());
        if (exists)
        {
            throw new EmailAlreadyExistsException("Email already exists! Try a different one.");
        }
        Customer customer=new Customer();
        customer.setName(registerRequest.getName());
        customer.setEmail(registerRequest.getEmail());
        customer.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        customerRepository.save(customer);
        return ResponseEntity.ok("You have successfully registered with ID : "+customer.getId());
    }
    @Transactional
    public ResponseEntity<?> registerAdmin(RegisterRequest registerRequest) throws EmailAlreadyExistsException {
        boolean exists=adminRepository.findByEmailExists(registerRequest.getEmail());
        if (exists)
        {
            throw new EmailAlreadyExistsException("Email already exists! Try a different one.");
        }
        Admin admin=new Admin();
        admin.setName(admin.getName());
        admin.setEmail(registerRequest.getEmail());
        admin.setPassword(admin.getPassword());
        adminRepository.save(admin);
        return ResponseEntity.ok("You have successfully registered with ID : "+admin.getId());
    }
    public ResponseEntity<?> login(LoginRequest loginRequest) throws UserNotFoundException
    {
        Admin admin=adminRepository.findById(loginRequest.getId()).orElse(null);
        Customer customer=customerRepository.findById(loginRequest.getId()).orElse(null);
        if(admin==null && customer==null)
        {
            throw new UserNotFoundException("Customer or Admin with id : "+loginRequest.getId()+" does not exist!");
        }
        else if(customer!=null)
        {
            return ResponseEntity.ok().body(jwtUtil.generateToken(loginRequest.getId().toString(),customer.getRole()));
        }
        else
        {
            return ResponseEntity.ok().body(jwtUtil.generateToken(loginRequest.getId().toString(),admin.getRole()));
        }
    }
}
