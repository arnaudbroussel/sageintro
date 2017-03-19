package com;

import java.util.List;

import com.mysage.entities.Customer;
import com.mysage.entities.Invoice;
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


	/***
	 * Create a customer
	 * (always asserted -> the save() method should inform the result of the operation)
	 * TODO : CRUD operations should return a result (OK / NOK)
	 */
	public void testAdd() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		Customer customer = new Customer("C4", "FNAC");

		service.save(customer);

		List<Customer> customers = service.findAllCustomers();
		
		for(Customer c:customers){
			System.out.println(c);
		}
		
		assertTrue(true);
	}

	/***
	 * Check that for an existing customer code, the customer is returned
	 */
	public void testFindCustomerOK() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		Customer customer = service.findByCode("C1");
		System.out.println(customer);
		
		assertTrue(customer != null);
	}

	/***
	 * Check that a wrong code for a customer return a null.
	 */
	public void testFindCustomerNOK() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		Customer customer = service.findByCode("_wrong_code_");
		
		assertTrue(customer == null);
	}	

	/***
	 * List all the customers and all the invoices for each.
	 * (always asserted)
	 */
	public void testFindAll() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		List<Customer> customers = service.findAllCustomers();
		
		for(Customer c:customers){
			System.out.println(c);
			for (Invoice i:c.getInvoices()){
				System.out.println(i);
			}
		}
		
		assertTrue(true);
	}	

}
