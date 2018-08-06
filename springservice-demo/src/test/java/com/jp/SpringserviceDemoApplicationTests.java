package com.jp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.api.ApiController;
import com.jp.api.dom.Transaction;
import com.jp.api.transaction.TransactionService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ApiController.class,secure=false)
public class SpringserviceDemoApplicationTests {

	@Autowired
    private MockMvc mvc;

	@MockBean
	private TransactionService transactionService;
	
	
	
	@InjectMocks
	ApiController apicontroler;
	
	Transaction tran = new Transaction();
	{
		tran.setAmount(12.3);
		tran.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		tran.setTransaction_id(3);
		tran.setCustomer("aa");
	}
	
	@Test
	public void testNewTransactions() {
		when(transactionService.addTrasaction(Mockito.any(Transaction.class))).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
		try {
			
			RequestBuilder requestBuilder =MockMvcRequestBuilders
					.post("/api/transactions")
					.accept(MediaType.APPLICATION_JSON).content(asJsonString(tran))
					.contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.CREATED.value(), response.getStatus());			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTransactions() {
		List<Transaction> list = new ArrayList<>();
		list.add(tran);
		when(transactionService.getTrasaction(Mockito.contains("srini"))).thenReturn(list);
		try {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/api/transaction/customer/srini"); 	
			
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			System.out.println(response.getContentAsString());
			String expected = "[{\"customer\":\"aa\",\"transaction_id\":3,\"amount\":12.3}]";
			JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteTrasaction() {
		List<Transaction> list = new ArrayList<>();
		list.add(tran);
		when(transactionService.deleteTrasaction(Mockito.anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.ACCEPTED));
		try {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
					"/api/transaction/delete/1"); 	
			
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testUpdateTransaction() {
		when(transactionService.updateTrasaction((Mockito.any(Transaction.class)))).thenReturn(new ResponseEntity<>(HttpStatus.ACCEPTED));
		try {
			
			RequestBuilder requestBuilder =MockMvcRequestBuilders
					.post("/api/transaction/update")
					.accept(MediaType.APPLICATION_JSON).content(asJsonString(tran))
					.contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        
	        
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
}
