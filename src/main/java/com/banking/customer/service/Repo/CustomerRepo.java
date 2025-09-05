package com.banking.customer.service.Repo;

import com.banking.customer.service.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    boolean existsByContactNo(String contactNo);

}
