package com.mysage.services;

import java.util.List;

import com.mysage.entities.Customer;
import com.mysage.entities.Invoice;


public interface ICustomerInvoiceService {

	public void save(Customer entity);	
	public void save(Invoice entity);	

	public void updateToPaid(Integer number);
	
	public Customer findByCode(String code);
	public Invoice findByNumber(Integer number);
	
	public List<Customer> findAll();
	
	public Float getInvoicesAmount(Customer entity);
	public Float getInvoicesAmount();
}
