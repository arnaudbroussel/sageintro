package com;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.mysage.entities.Customer;
import com.mysage.entities.Invoice;
import com.mysage.enums.InvoiceStatus;
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

	/***
	 * Get a customer and create a series of 3 invoices for it.
	 */
	public void testAdd() {
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

	/***
	 * Print out the total amount of the invoices
	 * (always asserted)
	 */
	public void testAmounts() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		Customer customer = service.findByCode("C1");

		System.out.println("Total amount = " + service.getInvoicesAmount());
		System.out.println("Total amount for customer " + customer.getCustomerName() + " = "
				+ service.getInvoicesAmount(customer));

		assertTrue(true);
	}

	/***
	 * Get a given given invoice from the DB
	 */
	public void testFindInvoice() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		Invoice invoice = service.findByNumber(3);

		assertTrue(invoice != null);
	}

	/***
	 * Change the status of a given invoice,
	 * get the invoice from the DB and check if the status corresponds to PAID
	 */
	public void testChangeStatus() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		service.updateToPaid(10);
		Invoice invoice = service.findByNumber(10);

		assertTrue(invoice.getStatus().equals(InvoiceStatus.PAID.text()));
	}

	/***
	 * List all the invoices of the DB
	 */
	public void testFindAll() {
		ICustomerInvoiceService service = new CustomerInvoiceService();

		List<Invoice> l = service.findAllInvoices();
		for (Invoice i : l) {
			System.out.println(i.toPrettyString());
		}
		assertTrue(true);
	}

}
