package com.mysage.enums;

public enum InvoiceStatus {
	PAID("Y"), PENDING("N");

	private String text;

	InvoiceStatus(String text) {
		this.text = text;
	}

	public String text() {
		return text;
	}

}
