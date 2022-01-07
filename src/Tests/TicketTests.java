package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.Ticket;


public class TicketTests {
    private Ticket ticket = null;

    @BeforeEach
    void setUp()
    {
        ticket = new Ticket();
    }

    // Test for selecting amount of tickets
    @Test
    void testAmountOfTickets() {
        ticket.setNumOfTypeTickets("Student", 3);
        assertEquals(3, ticket.getNumOfTypeTickets("Student"));
    }
}
