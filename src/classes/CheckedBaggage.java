package classes;

public class CheckedBaggage extends Product {
	private String ticketCode;							//Could an int type be used here?

	public String getTicketCode() {						//Ticket code getter
		return ticketCode;
	}

	public void setTicketCode(String ticketCode) {		//Ticket code setter
		this.ticketCode = ticketCode;
	}
	
	public CheckedBaggage(String code, String type, String ticketCode) {	//Checked baggage constructor
		super(code, type);
		this.ticketCode = ticketCode;
	}
}