package com.mysage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import com.mysage.entities.Customer;
import com.mysage.entities.Invoice;
import com.mysage.services.CustomerInvoiceService;
import com.mysage.services.ICustomerInvoiceService;

public class App {

	private static final String EMPTY_STRING = "";

	public static void main(String[] args) throws IOException {

		ICustomerInvoiceService service = new CustomerInvoiceService();

		String codeCustomer;
		do {

			codeCustomer = showLogin(service);
			if (!codeCustomer.equalsIgnoreCase("exit")) {

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
							createInvoice(service, customer);
							break;
						case 3:
							payInvoice(service);
							break;
						case 4:
							customerInvoices(service, customer);
							break;
						default:
						}

					} while (choiceMenu != 0);
				}
			}
		} while (!codeCustomer.equalsIgnoreCase("exit"));

		System.out.println("Bye...");
	}

	private static String showLogin(ICustomerInvoiceService service) {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("--- Customer & Invoices Application (LOGIN) ---");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++\n");

		customersList(service);

		return inputText("\nEnter customer code ('exit' to exit application): ");
	}

	private static int showMenu(Customer customer) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("--- Customer & Invoices Application (MENU) ---");
		System.out.println("User : " + customer.getCustomerCode().concat(" - ").concat(customer.getCustomerName()));
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++\n");
		System.out.println("1) Create customer");
		System.out.println("2) Create invoice");
		System.out.println("3) Pay invoice");
		System.out.println("4) List of invoices");
		System.out.println("0) ...exit\n");

		return inputInteger("Choice : ");
	}

	public static void createCustomer(ICustomerInvoiceService service) {
		System.out.println("\n-- Create customer --");

		String code = inputText("Code: ");
		String name = inputText("Name: ");

		service.save(new Customer(code, name));
	}

	private static void customersList(ICustomerInvoiceService service) {
		System.out.println("\n-- Customers list --");

		for (Customer c : service.findAll()) {
			System.out.println(c);
		}
	}

	private static void createInvoice(ICustomerInvoiceService service, Customer customer) {
		System.out.println("\n-- Create invoice --");

		Date date = inputDate("Date (yyyy-MM-dd): ");
		String description = inputText("Description: ");
		Float amount = inputFloat("Amount: ");

		service.save(new Invoice(customer, description, amount, date));
	}

	private static void payInvoice(ICustomerInvoiceService service) {
		System.out.println("\n-- Pay an invoice (change status) --");

		Integer invoiceNumber = inputInteger("Invoice number: ");
		if (!service.updateToPaid(invoiceNumber)) {
			System.out.println("\nStatus not changed.\n");
		} else {
			System.out.println("\nInvoice is now paid.\n");
		}
	}

	private static void customerInvoices(ICustomerInvoiceService service, Customer customer) {
		if ((customer.getInvoices() != null) && (customer.getInvoices().size() > 0)) {
			for (Invoice invoice : customer.getInvoices()) {
				System.out.println(invoice);
			}
			System.out.println("\nTotal amount = " + String.format("%.2f", service.getInvoicesAmount(customer)));
			System.out.println("Number of invoices = " + customer.getInvoices().size());
			System.out.println("Average amount = "
					+ String.format("%.2f", service.getInvoicesAmount(customer) / customer.getInvoices().size()));
		} else {
			System.out.println("Number of invoices = 0");
		}
		System.out.println();
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

	private static Float inputFloat(String text) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print(text);
			return Float.parseFloat(br.readLine());
		} catch (IOException e) {
			return 0f;
		}
	}

	private static Date inputDate(String text) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Date date = null;
		while (date == null) {
			System.out.print(text);
			String input = scanner.nextLine();
			try {
				date = format.parse(input);
			} catch (ParseException e) {
				System.out.println("Date format not valid...");
			}
		}
		return date;
	}
}