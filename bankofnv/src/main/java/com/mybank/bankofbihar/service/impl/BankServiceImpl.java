package com.mybank.bankofbihar.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.bankofbihar.common.AESEncryptDecrypthandler;
import com.mybank.bankofbihar.common.CommonConstants;
import com.mybank.bankofbihar.common.RSAEncryptDecryptHandler;
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
	
	@Autowired
	private CommonConstants constants;

	@Override
	public User createUser(User user) {
		//PublicKey publicKey = getPublicKey();
		try {
			user.setPassword(AESEncryptDecrypthandler.encrypt(user.getPassword(), constants.getSecret()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(user.getPhone().length()==10) {
			user.setPhone("91"+user.getPhone());
		}else if(user.getPhone().length()==11){
			user.setPhone("91"+user.getPhone().substring(1));
		}else if(user.getPhone().length()==13){
			user.setPhone(user.getPhone().substring(1));
		}else if(user.getPhone().length()<10) {
			return null;
		}
		User newUser = userRepo.save(user);
		return newUser;
	}

	private PublicKey getPublicKey() {
		byte[] publicKeyBytes;
		PublicKey publicKey = null;
		try {
			publicKeyBytes = Files.readAllBytes(new File(constants.getPublicKeyPath()).toPath());
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	@Override
	public Optional<User> getUser(int id) {
		Optional<User> user = userRepo.findById(id);
		try {
			user.get().setPassword(AESEncryptDecrypthandler.decrypt(user.get().getPassword(),constants.getSecret()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepo.findAll();
		for(User user : users) {
			try {
				user.setPassword(AESEncryptDecrypthandler.decrypt(user.getPassword(),constants.getSecret()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		try {
			newUser.setPassword(AESEncryptDecrypthandler.encrypt(user.getPassword(), constants.getSecret()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(oldUser.isPresent()) {
			userRepo.save(newUser);
			return "User " + user.getName() +" updated successfully";
		}
		return null;
	}

}
