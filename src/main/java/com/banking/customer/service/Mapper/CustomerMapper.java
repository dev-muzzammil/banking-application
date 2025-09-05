package com.banking.customer.service.Mapper;

import com.banking.customer.service.DTO.CustomerRequestDTO;
import com.banking.customer.service.DTO.CustomerResponseDTO;
import com.banking.customer.service.Entity.Customer;
import org.mapstruct.*;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDTO toDto(Customer customer);

    @Mapping(target = "id" , ignore = true)
    Customer toEntity(CustomerRequestDTO requestDTO);

    @Mapping(target = "id" , ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromRequest(CustomerRequestDTO customerRequestDTO, @MappingTarget Customer customer);

}
