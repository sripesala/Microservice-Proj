package com.jp.api;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.api.dom.Transaction;
import com.jp.api.transaction.TransactionService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	TransactionService transactionService;
	
	@Autowired
	public ApiController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	/**
	 * Every Time a new transaction happened, a new record will be added.
	 * @param tran
	 * @return
	 */
	
	@PostMapping("/transactions")
	public ResponseEntity<?> addTrasaction(@RequestBody Transaction tran) {
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
		System.out.println(Timestamp.valueOf(localDateTime).getTime());

		return transactionService.addTrasaction(tran);
	}
	
	
	/**
	 * Enquire the transaction
	 * @param userid
	 * @return
	 */
	
	@GetMapping("/transaction/customer/{customerid}")
	public List<Transaction> getTransactionforcustomer(@PathVariable String customerid) {
		System.out.println("getTransactionforcustomer ...."+customerid);
		return transactionService.getTrasaction(customerid);
	}
	
	/**
	 * It updates customer transaction.
	 * @param userid
	 * @return
	 */
	
	@PostMapping("/transaction/update")
	public ResponseEntity<?>  updateTransaction(@RequestBody Transaction tran) {
		return transactionService.updateTrasaction(tran);
	}
	
	/**
	 * deletes the transaction of customer.
	 * @param userid
	 * @return
	 */
	
	@DeleteMapping("/transaction/delete/{transId}")
	public ResponseEntity<?> deleteTransactionforcustomer(@PathVariable String transId) {
		return transactionService.deleteTrasaction(Integer.parseInt(transId));
	}
	
	
	
}
