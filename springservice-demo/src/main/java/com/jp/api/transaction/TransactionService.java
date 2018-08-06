package com.jp.api.transaction;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jp.api.dom.Transaction;

public interface TransactionService {

	ResponseEntity<?> addTrasaction(Transaction tran);
	List<Transaction> getTrasaction(String customerid);
	ResponseEntity<?> deleteTrasaction(int transactionid);
	ResponseEntity<?> updateTrasaction(Transaction tran);


}
