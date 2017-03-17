package com.mysage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppMenu {

	public static int SeeMenu() {

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Wellcome to Customer & Invoices Application");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\n\n");
		System.out.println("1) Customers list");
		System.out.println("2) Invoices by customer");
		System.out.println("3) Create customers");
		System.out.println("4) Create invoice");
		System.out.println("5) Change invoice status");
		System.out.println("0) ...exit");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Choice : ");
		try {
			int menuOption = Integer.parseInt(br.readLine());
			return menuOption;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

}
