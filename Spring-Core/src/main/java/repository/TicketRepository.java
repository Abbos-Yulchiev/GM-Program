package repository;

import entity.Ticket;

import java.util.List;

public interface TicketRepository {

    List<Ticket> getBookedTickets(int userId, int pageSize, int pageNum);

    List<Ticket> getBookedTickets(long eventId, int pageSize, int pageNum);

    Ticket bookTicket(int userId, long eventId, int place, Ticket.Categories categories);

    boolean cancelTicket(long ticketId);
}
