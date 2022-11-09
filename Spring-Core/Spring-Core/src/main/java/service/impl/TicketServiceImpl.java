package service.impl;

import entity.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import repository.TicketRepository;
import service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public ResponseEntity<List<Ticket>> getBookedTickets(int userId, int pageSize, int pageNum) {
        List<Ticket> bookedTickets = ticketRepository.getBookedTickets(userId, pageSize, pageNum);
        return ResponseEntity.status(HttpStatus.OK).body(bookedTickets);
    }

    @Override
    public ResponseEntity<List<Ticket>> getBookedTickets(long eventId, int pageSize, int pageNum) {
        List<Ticket> bookedTickets = ticketRepository.getBookedTickets(eventId, pageSize, pageNum);
        return ResponseEntity.status(HttpStatus.OK).body(bookedTickets);
    }


    @Override
    public ResponseEntity<Ticket> bookTicket(int userId, long eventId, int place, Ticket.Categories categories) {
        Ticket ticket = ticketRepository.bookTicket(userId, eventId, place, categories);
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @Override
    public ResponseEntity<Boolean> cancelTicket(long id) {
        boolean status = ticketRepository.cancelTicket(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
