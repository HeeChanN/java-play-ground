package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TheaterTest {

    @Test
    public void enterAudienceWithCash(){
        //given
        Audience audience = new Audience(new Bag(1000L));
        TicketOffice ticketOffice = new TicketOffice(1000L, new Ticket());
        TicketSeller ticketSeller = new TicketSeller(ticketOffice);
        Theater theater = new Theater(ticketSeller);

        //when
        theater.enter(audience);

        //then
        assertThat(audience.getBag().hasTicket()).isTrue();
    }
}
