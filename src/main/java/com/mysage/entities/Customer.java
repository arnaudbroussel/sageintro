package com.mysage.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "customer", catalog = "sql11164259", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CUSTOMER_CODE") })
public class Customer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8973515275943834600L;


	private Integer customerId;
	private String customerCode;
	private String customerName;
	private Set<Invoice> invoices = new HashSet<Invoice>(0);

	public Customer() {
	}

	public Customer(String customerCode, String customerName) {
		this.customerCode = customerCode;
		this.customerName = customerName;
	}

	public Customer(String customerCode, String customerName, Set<Invoice> invoices) {
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.invoices = invoices;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CUSTOMER_ID", unique = true, nullable = false)
	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "CUSTOMER_CODE", unique = true, nullable = false, length = 100)
	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	@Column(name = "CUSTOMER_NAME", unique = true, nullable = false, length = 200)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerCode=" + customerCode + ", customerName=" + customerName + "]";
	}
}