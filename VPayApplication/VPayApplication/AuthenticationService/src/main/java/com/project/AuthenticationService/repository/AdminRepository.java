package com.project.AuthenticationService.repository;

import com.project.AuthenticationService.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    boolean findByEmailExists(String email);
}
