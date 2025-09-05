package com.banking.customer.service.Controller;

import com.banking.customer.service.DTO.CustomerRequestDTO;
import com.banking.customer.service.DTO.CustomerResponseDTO;
import com.banking.customer.service.Entity.Customer;
import com.banking.customer.service.Exception.ErrorResponse;
import com.banking.customer.service.Service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-service")
@RequiredArgsConstructor
@Tag(name = "Customer-service APIs", description = "CRUD")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Create a new User",
            description = "This endpoint is used to create a new user in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "New User Created"),
            @ApiResponse(responseCode = "500" , description = "Internal server error",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
    })
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO responseDTO = customerService.createCustomer(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get User by Id",
            description = "Fetches a user by their unique id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }


    @Operation(
            summary = "Get All Users",
            description = "Fetched a list of all users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllUsers());
    }


    @Operation(
            summary = "Update User",
            description = "Update User by Id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User update successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateUserById(@PathVariable Long id, @RequestBody CustomerRequestDTO requestDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(id, requestDTO));
    }


    @Operation(
            summary = "Delete user",
            description = "Delete a user by their id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }


}
