package com.mybank.bankofbihar.service.impl;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Optional<User> getUser(int id) {
		return userRepo.findById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public String deleteUser(int id) {
		Optional<User> user = getUser(id);
		if(user.isPresent()) {
			userRepo.delete(user.get());
			return "User " + user.get().getName() +" deleted successfully";
		}
		return "User Not Found";
	}

	@Override
	public String updateUser(User user) {
		Optional<User> oldUser = getUser(user.getId());
		User newUser = oldUser.get();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setPhone(user.getPhone());
		newUser.setPassword(user.getPassword());
		if(oldUser.isPresent()) {
			userRepo.save(newUser);
			return "User " + user.getName() +" updated successfully";
		}
		return null;
	}

}
