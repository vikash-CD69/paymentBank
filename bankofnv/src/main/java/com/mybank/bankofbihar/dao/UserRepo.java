package com.mybank.bankofbihar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.bankofbihar.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
