package com.workintech.s18d4.service;

import com.workintech.s18d4.dao.CustomerRepository;
import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer oldCustomer = findById(id);
        oldCustomer.setFirstName(customer.getFirstName());
        oldCustomer.setLastName(customer.getLastName());
        oldCustomer.setAccounts(customer.getAccounts());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setSalary(customer.getSalary());
        return customerRepository.save(oldCustomer);
    }

    @Override
    public Customer delete(Long id) {
        Customer customer = findById(id);
        customerRepository.delete(customer);
        return customer;
    }
}
