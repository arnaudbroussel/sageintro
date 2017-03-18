package com;

import java.util.Date;
import java.util.Random;

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
public class InvoiceTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public InvoiceTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(InvoiceTest.class);
	}

	public void _testAdd() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		boolean result = false;

		Customer customer = service.findByCode("C1");

		if (customer != null) {
			for (int i = 0; i < 3; i++) {
				Invoice invoice = new Invoice();

				Random random = new Random();
				invoice.setAmount(new Float(random.nextFloat() * (1000)));

				invoice.setCustomer(customer);
				invoice.setDate(new Date());
				invoice.setDescription("JUnit created invoice");

				invoice.setCustomer(customer);
				customer.getInvoices().add(invoice);

				service.save(invoice);
			}
			result = true;
		}

		assertTrue(result);
	}

	public void testAmounts() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		Customer customer = service.findByCode("C1");

		System.out.println("Total amount = " + service.getInvoicesAmount());
		System.out.println(
				"Total amount for customer " + customer.getCustomerName() + " = " + service.getInvoicesAmount(customer));

		assertTrue(true);
	}

	public void testFindInvoice() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		Invoice invoice = service.findByNumber(3);

		assertTrue(invoice != null);
	}

	public void testChangeStatus() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		service.updateToPaid(10);
		service.updateToPaid(14);
		
		assertTrue(true);
	}

	
}
