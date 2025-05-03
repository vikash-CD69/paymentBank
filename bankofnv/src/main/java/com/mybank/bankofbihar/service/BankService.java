package com.mybank.bankofbihar.service;

import java.util.List;
import java.util.Optional;

import com.mybank.bankofbihar.entity.User;

public interface BankService {

	public User createUser(User user);

	public Optional<User> getUser(int userId);

	public List<User> getAllUsers();

	public String deleteUser(int id);

	public String updateUser(User user);

}
