package service;

import entity.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {


    ResponseEntity<List<Ticket>> getBookedTickets(int userId, int pageSize, int pageNum);

    ResponseEntity<List<Ticket>> getBookedTickets(long eventId, int pageSize, int pageNum);

    ResponseEntity<Ticket> bookTicket(int userId, long eventId, int place, Ticket.Categories categories);

    ResponseEntity<Boolean> cancelTicket(long id);
}
