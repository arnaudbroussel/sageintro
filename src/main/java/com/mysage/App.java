package com.mysage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.plaf.InputMapUIResource;

import com.mysage.entities.Customer;
import com.mysage.entities.Invoice;
import com.mysage.services.CustomerInvoiceService;
import com.mysage.services.ICustomerInvoiceService;

public class App {

	private static final String EMPTY_STRING = "";

	public static void main(String[] args) throws IOException {

		String codeCustomer;
		do {

			codeCustomer = showLogin();
			if (!codeCustomer.equalsIgnoreCase("exit")) {

				ICustomerInvoiceService service = new CustomerInvoiceService();
				Customer customer = service.findByCode(codeCustomer);

				if (customer != null) {
					int choiceMenu;
					do {
						choiceMenu = showMenu(customer);

						switch (choiceMenu) {
						case 1:
							createCustomer(service);
							break;
						case 2:
							customersList(service);
							break;
						case 3:
							createInvoice(service);
							break;
						case 4:
							payInvoice(service);
							break;
						case 5:
							customerInvoices(service, customer);
							break;
						default:
						}

					} while (choiceMenu != 0);
				}
			}
		} while (!codeCustomer.equalsIgnoreCase("exit"));

		System.out.println("Exit...");
	}

	private static String showLogin() {
		cls();

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("--- Customer & Invoices Application (LOGIN) ---");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++\n");

		return inputText("Enter customer code ('exit' to exit application): ");
	}

	private static int showMenu(Customer customer) {
		cls();

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("--- Customer & Invoices Application (MENU) ---");
		System.out.println(customer.getCustomerCode().concat(" - ").concat(customer.getCustomerName()));
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++\n");
		System.out.println("1) Create customers");
		System.out.println("2) Customers list");
		System.out.println("3) Create invoice");
		System.out.println("4) Pay invoice");
		System.out.println("5) Your invoices");
		System.out.println("0) ...exit\n");

		return inputInteger("Choice : ");
	}

	public static void createCustomer(ICustomerInvoiceService service) {
		System.out.println("\n-- Create customers-- ");

		String code = inputText("Code: ");
		String name = inputText("Name: ");

		service.save(new Customer(code, name));
	}

	private static void customersList(ICustomerInvoiceService service) {
		System.out.println("\n-- Customers list -- ");

		for (Customer c : service.findAll()) {
			System.out.println(c);
		}
	}

	private static void createInvoice(ICustomerInvoiceService service) {

	}

	private static void payInvoice(ICustomerInvoiceService service) {
		System.out.println("\n-- Pay an invoice (change status) -- ");

		Integer invoiceNumber = inputInteger("Invoice number: ");
		service.updateToPaid(invoiceNumber);
	}

	private static void customerInvoices(ICustomerInvoiceService service, Customer customer) {
		if ((customer.getInvoices() != null) && (customer.getInvoices().size() > 0)) {
			for (Invoice invoice : customer.getInvoices()) {
				System.out.println(invoice);
			}
			System.out.println("\nTotal amount = " + service.getInvoicesAmount(customer));
			System.out.println("Number of invoices = " + customer.getInvoices().size());
			System.out.println("Average amount = " + service.getInvoicesAmount(customer) / customer.getInvoices().size());
		}
		else {
			System.out.println("Number of invoices = 0");
		}
	}

	private static void cls() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception e) {
		}
	}

	private static String inputText(String text) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print(text);
			return br.readLine();
		} catch (IOException e) {
			return EMPTY_STRING;
		}
	}

	private static Integer inputInteger(String text) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print(text);
			return Integer.parseInt(br.readLine());
		} catch (IOException e) {
			return 0;
		}
	}

}