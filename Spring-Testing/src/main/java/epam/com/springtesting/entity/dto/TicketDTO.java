package epam.com.springtesting.entity.dto;

import epam.com.springtesting.entity.Ticket;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class TicketDTO {


    private long eventId;

    @Enumerated(EnumType.STRING)
    private Ticket.Categories categories;

    private int address;

    private double price;

    private int count;
}
