package com.banking.customer.service.Service;

import com.banking.customer.service.DTO.CustomerRequestDTO;
import com.banking.customer.service.DTO.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO);

    public CustomerResponseDTO getCustomerById(Long id);

    public List<CustomerResponseDTO> getAllUsers();

    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO);

    public void deleteCustomer(Long id);

}
