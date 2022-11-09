package epam.com.springtesting.service;

import epam.com.springtesting.entity.Ticket;
import epam.com.springtesting.entity.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    String bookTicket(int userId, long eventId, long address, Ticket.Categories categories, int amount);

    List<Ticket> getBookedTicketsByUserId(int userId, Integer pageSize, Integer pageNum);

    List<Ticket> getBookedTicketsByEventId(long eventId, int pageSize, int pageNum);

    String createTicket(TicketDTO ticketDTO);

    String cancelTicket(int userId, long eventId, long addressId, Ticket.Categories categories);
}
