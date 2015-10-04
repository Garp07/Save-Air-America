package com.airamerica.product;

public class CheckedBaggage extends Service {
	private Ticket ticketCode;							
	
	public CheckedBaggage(String code, Ticket ticketCode) {
		super(code);
		this.ticketCode = ticketCode;
	}
}