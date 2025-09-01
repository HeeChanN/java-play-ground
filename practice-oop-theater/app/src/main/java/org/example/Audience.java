package org.example;

public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public void payAmount(Long amount){
        bag.minusAmount(amount);
    }

    public boolean hasInvitation(){
        return bag.hasInvitation();
    }

    public boolean hasTicket(){
        return bag.hasTicket();
    }

    public void receiveTicket(Ticket ticket){
        bag.setTicket(ticket);
    }
}
