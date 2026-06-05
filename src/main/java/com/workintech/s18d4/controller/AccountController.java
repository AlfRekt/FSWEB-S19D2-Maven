package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    private AccountResponse convertToResponse(Account account) {
        return new AccountResponse(
                account.getId().intValue(),
                account.getAccountName(),
                account.getMoneyAmount()
        );
    }

    @GetMapping
    public List<AccountResponse> findAll() {
        return accountService.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable Long id) {
        return convertToResponse(accountService.findById(id));
    }


    @PostMapping("/{customerId}")
    public AccountResponse save(@PathVariable Long customerId,
                                @RequestBody Account account) {

        Customer customer = customerService.findById(customerId);
        account.setCustomer(customer);

        Account savedAccount = accountService.save(account);

        return convertToResponse(savedAccount);
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@PathVariable Long customerId,
                                  @RequestBody Account account) {

        Customer customer = customerService.findById(customerId);

        Account existingAccount = accountService.findById(account.getId());

        existingAccount.setAccountName(account.getAccountName());
        existingAccount.setMoneyAmount(account.getMoneyAmount());
        existingAccount.setCustomer(customer);

        Account updatedAccount = accountService.save(existingAccount);

        return convertToResponse(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable Long id) {

        accountService.findById(id);

        Account deletedAccount = accountService.delete(id);

        return convertToResponse(deletedAccount);
    }
}