package com.jp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jp.api.dom.Transaction;
import java.lang.String;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	
	List<Transaction> findByCustomer(String customer);
}
