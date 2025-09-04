package org.example;

public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    public Bag(Long amount){
        this.amount = amount;
    }

    public Bag(Invitation invitation, Long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }

    public Long addTicket(Ticket ticket){
        if(hasInvitation()){
            setTicket(ticket);
            return 0L;
        }
        else{
            setTicket(ticket);
            minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }

    public boolean hasTicket() {
        return ticket != null;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }

    private boolean hasInvitation() {
        return invitation != null;
    }

    private void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    private void minusAmount(Long amount) {
        this.amount -= amount;
    }
}
