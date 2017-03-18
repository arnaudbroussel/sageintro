package com;

import java.util.List;

import com.mysage.entities.Customer;
import com.mysage.services.CustomerInvoiceService;
import com.mysage.services.ICustomerInvoiceService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CustomerTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public CustomerTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(CustomerTest.class);
	}


	public void testAdd() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		Customer customer = new Customer("C3", "EVIAN");

		service.save(customer);

		assertTrue(true);
	}


	
	public void testFindCustomerOK() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		Customer customer = service.findByCode("C1");
		System.out.println(customer);
		
		assertTrue(customer != null);
	}

	public void testFindCustomerNOK() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		Customer customer = service.findByCode("_wrong_code_");
		
		assertTrue(customer == null);
	}	

	public void testFindAll() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		List<Customer> customers = service.findAll();
		
		for(Customer c:customers){
			System.out.println(c);
		}
		
		assertTrue(true);
	}	

}
