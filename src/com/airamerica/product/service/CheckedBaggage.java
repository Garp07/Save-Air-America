package com.airamerica.product.service;

import com.airamerica.product.ticket.Ticket;

public class CheckedBaggage extends Service {
	private Ticket ticketCode;	

	public Ticket getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(Ticket ticketCode) {
		this.ticketCode = ticketCode;
	}
	
	//CheckedBaggage constructor
	public CheckedBaggage(String code, Ticket ticketCode, int quantity) {
		super(code);
		this.ticketCode = ticketCode;
		this.type = "SC";
		
		this.quantity = quantity;
		
		if(this.quantity == 1) {
			this.subtotal = 25;
		} else {
			this.subtotal = 25 + (quantity - 1) * 25;
		}
		this.taxes = 0.04 * subtotal;
		this.total = subtotal + taxes;
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		String itemDescription = String.format("Baggage (%d units @ $%.2f for 1st and $%.2f onwards)", quantity, 25, 35);
		
		sb.append(String.format("%-10s %-60s $%10.2f $%10.2f $%10.2f", code, itemDescription, subtotal, taxes, total));
		
		return sb.toString();
	}

//	public double getCost() {
//		double cost = 0;
//		if(this.quantity == 1) {
//			cost = 25;
//		} else {
//			cost = 25 + ((quantity-1)*35.0);
//		}
//		return cost;
//	}
}