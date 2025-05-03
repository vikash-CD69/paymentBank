package com.mybank.bankofbihar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.bankofbihar.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>{

}
