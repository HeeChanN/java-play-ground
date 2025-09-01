package org.example;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public void sellTo(Audience audience){
        if(audience.hasInvitation()){
            Ticket ticket = ticketOffice.getTicket();
            audience.receiveTicket(ticket);
        }
        else{
            Ticket ticket = ticketOffice.getTicket();
            audience.payAmount(ticket.getFee());
            ticketOffice.plusAmount(ticket.getFee());
            audience.receiveTicket(ticket);
        }
    }
}
