package com.mybank.bankofbihar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.bankofbihar.entity.User;
import com.mybank.bankofbihar.service.BankService;

@RestController
@RequestMapping("/banking")
public class BankController {
	
	@Autowired
	private BankService bnkServ;
	
	@PostMapping("/add-user")
	public ResponseEntity<Object> createUser(@RequestBody User user){
		if(user.getName() != null ||
				user.getPhone() != null ||
				user.getEmail() != null ||
				user.getPassword() != null) {
			User newUser = bnkServ.createUser(user);
			return new ResponseEntity<Object>(newUser, HttpStatus.CREATED);
		}
		return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		
	}

}
