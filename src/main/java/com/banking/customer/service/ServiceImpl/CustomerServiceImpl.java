package com.banking.customer.service.ServiceImpl;

import com.banking.customer.service.DTO.CustomerRequestDTO;
import com.banking.customer.service.DTO.CustomerResponseDTO;
import com.banking.customer.service.Entity.Customer;
import com.banking.customer.service.Exception.ResourceNotFoundException;
import com.banking.customer.service.Mapper.CustomerMapper;
import com.banking.customer.service.Repo.CustomerRepo;
import com.banking.customer.service.Service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO) {

        if (customerRepo.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        if (customerRepo.existsByContactNo(requestDTO.getContactNo())) {
            throw new IllegalArgumentException("Contact number already registered");
        }
        Customer customer = customerMapper.toEntity(requestDTO);
        return customerMapper.toDto(customerRepo.save(customer));
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAllUsers() {
        return customerRepo.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        customerMapper.updateFromRequest(requestDTO, customer);
        return customerMapper.toDto(customerRepo.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepo.existsById(id)) throw new ResourceNotFoundException("User not found with id " + id);
        customerRepo.deleteById(id);
    }


}
