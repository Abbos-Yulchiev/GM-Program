package controller;


import entity.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/getBookedTickets/{userId}")
    public ResponseEntity<List<Ticket>> getBookedTicketsByUserId(@RequestParam int userId,
                                                                 @RequestParam(defaultValue = "0", required = false) Integer pageSize,
                                                                 @RequestParam(defaultValue = "20", required = false) Integer pageNum) {
        return ticketService.getBookedTickets(userId, pageSize, pageNum);
    }

    @GetMapping("/getBookedTickets/{eventId}")
    public ResponseEntity<List<Ticket>> getBookedTicketsByEventId(@RequestParam long eventId,
                                                                  @RequestParam(defaultValue = "0", required = false) int pageSize,
                                                                  @RequestParam(defaultValue = "20", required = false) int pageNum) {
        return ticketService.getBookedTickets(eventId, pageSize, pageNum);
    }

    @PostMapping("/bookTicket")
    public ResponseEntity<Ticket> bookTicket(@RequestParam int userId,
                                             @RequestParam long eventId,
                                             @RequestParam int place,
                                             @RequestParam Ticket.Categories categories) {
        return ticketService.bookTicket(userId, eventId, place, categories);
    }

    @DeleteMapping("/cancelTicket/{id}")
    public ResponseEntity<Boolean> cancelTicket(@PathVariable long id) {
        return ticketService.cancelTicket(id);
    }
}

/**
 * Ticket bookTicket(long userId, long eventId, int place, Ticket.Categories category);
 * List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);
 * List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);
 * boolean cancelTicket(long ticketId);
 */
