package com.jp.api.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jp.api.dom.Transaction;
import com.jp.api.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionRepository transrepo;
	
	@Override
	public ResponseEntity<?> addTrasaction(Transaction tran) {
		transrepo.save(tran);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@Override
	public List<Transaction> getTrasaction(String customerid) {
		return transrepo.findByCustomer(customerid);
	}

	@Override
	public ResponseEntity<?> deleteTrasaction(int transactionid) {
		Optional<Transaction> existingtran = transrepo.findById(transactionid);
		if(existingtran.isPresent()) {
			transrepo.deleteById(transactionid);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		try {
			existingtran.orElseThrow(()->new Exception("This transaction doesn't exist, please create before the deletion"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<?> updateTrasaction(Transaction tran) {
		Optional<Transaction> existingtran = transrepo.findById(tran.getTransaction_id());
		if(existingtran.isPresent()) {
			transrepo.save(tran);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);

		}
		try {
			existingtran.orElseThrow(()->new Exception("This transaction doesn't exist, please create before updating"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	
}
