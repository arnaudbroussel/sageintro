package com.mysage.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.mysage.enums.InvoiceStatus;

@Entity
@Table(name = "invoice", catalog = "sql11164259", uniqueConstraints = @UniqueConstraint(columnNames = "INVOICE_NUMBER"))
public class Invoice implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5504747409195739795L;
	private Integer invoiceId;
	private Integer invoiceNumber;
	private Customer customer;
	private String description;
	private String status;
	private Float amount;
	private Date date;

	public Invoice() {
		this.status = InvoiceStatus.PENDING.text();
	}

	public Invoice(Integer invoiceId, Integer invoiceNumber, Customer customer, String description, Float amount,
			Date date) {
		this();
		this.invoiceId = invoiceId;
		this.invoiceNumber = invoiceNumber;
		this.customer = customer;
		this.description = description;
		this.amount = amount;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "INVOICE_ID", unique = true, nullable = false)
	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "INVOICE_AMOUNT", precision = 10)
	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Column(name = "INVOICE_NUMBER", unique = true, nullable = false)
	public Integer getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Column(name = "INVOICE_DESCRIPTION", length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "INVOICE_STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INVOICE_DATE", unique = false, nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceNumber=" + invoiceNumber + ", customer=" + customer
				+ ", description=" + description + ", status=" + status + ", amount=" + amount + ", date=" + date + "]";
	}

}