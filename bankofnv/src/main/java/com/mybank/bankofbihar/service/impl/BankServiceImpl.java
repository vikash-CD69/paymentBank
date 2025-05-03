package com.mybank.bankofbihar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.bankofbihar.dao.AccountRepo;
import com.mybank.bankofbihar.dao.UserRepo;
import com.mybank.bankofbihar.entity.User;
import com.mybank.bankofbihar.service.BankService;

@Service
public class BankServiceImpl implements BankService{
	
	@Autowired
	private UserRepo userRepo; 
	
	@Autowired
	private AccountRepo accRepo;

	@Override
	public User createUser(User user) {
		User newUser = userRepo.save(user);
		return newUser;
	}

}
