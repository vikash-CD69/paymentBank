package com.mybank.bankofbihar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/user")
	public ResponseEntity<Object> getUser(@RequestParam(name = "id", required = false) String id){
		User user = null;
		if(id != null) {
			int userId = Integer.parseInt(id); 
			Optional<User> userOp = bnkServ.getUser(userId);
			if(userOp.isPresent()) {
				user = userOp.get(); 
			}
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}else {
			List<User> users = bnkServ.getAllUsers();
			return new ResponseEntity<Object>(users, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		String res = bnkServ.deleteUser(id);
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@PutMapping("/update-user")
	public ResponseEntity<Object> updateUser(@RequestBody User user){
		String res = bnkServ.updateUser(user);
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

}
