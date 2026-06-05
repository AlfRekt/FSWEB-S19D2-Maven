package com.workintech.s18d4.service;

import com.workintech.s18d4.dao.AccountRepository;
import com.workintech.s18d4.dao.CustomerRepository;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Autowired
    public AccountServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            return account.get();
        }
        return null;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Long id, Account account) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            account.setAccountName(account.getAccountName());
            account.setMoneyAmount(account.getMoneyAmount());
            account.setCustomer(customer.get());
            customer.get().getAccounts().add(account);
            return accountRepository.save(account);
        }
        return null;
    }

    @Override
    public Account delete(Long id) {
        Account account = findById(id);
        accountRepository.delete(account);
        return account;
    }
}
