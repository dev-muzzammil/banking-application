package com.banking.customer.service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "email", nullable = false , unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "contact_no", nullable = false , unique = true)
    private String contactNo;

    @Column(name = "address", nullable = false)
    private String address;

}
