package com.airamerica.product.service;

import com.airamerica.product.ticket.Ticket;

public class CheckedBaggage extends Service {
	private Ticket ticketCode;	
	private int noOfBags;
	
	public int getNoOfBags() {
		return noOfBags;
	}

	public void setNoOfBags(int noOfBags) {
		this.noOfBags = noOfBags;
	}

	public Ticket getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(Ticket ticketCode) {
		this.ticketCode = ticketCode;
	}
	
	//CheckedBaggage constructor
	public CheckedBaggage(String code, Ticket ticketCode) {
		super(code);
		this.ticketCode = ticketCode;
		this.type = "SC";
	}
}