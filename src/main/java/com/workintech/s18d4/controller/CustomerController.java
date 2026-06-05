package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private CustomerResponse convertToResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId().intValue(),
                customer.getEmail(),
                customer.getSalary()
        );
    }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id) {
        return convertToResponse(customerService.findById(id));
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer) {
        return convertToResponse(customerService.save(customer));
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @RequestBody Customer customer) {
        return convertToResponse(customerService.update(id, customer));
    }

    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable Long id) {
        return convertToResponse(customerService.delete(id));
    }
}