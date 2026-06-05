package com.workintech.s18d4.dto;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Address;

import java.util.List;

public record CustomerResponse(Integer id, String email, Double salary) {
}
