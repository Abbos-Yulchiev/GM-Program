package epam.com.springtesting.controller;


import epam.com.springtesting.entity.Ticket;
import epam.com.springtesting.entity.dto.TicketDTO;
import epam.com.springtesting.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/getBookedTicketsByUser")
    public List<Ticket> getBookedTicketsByUserId(@RequestParam int userId,
                                                 @RequestParam(defaultValue = "0", required = false) Integer pageSize,
                                                 @RequestParam(defaultValue = "20", required = false) Integer pageNum) {
        return ticketService.getBookedTicketsByUserId(userId, pageSize, pageNum);
    }

    @GetMapping("/getBookedTicketsByEvent")
    public List<Ticket> getBookedTicketsByEventId(@RequestParam long eventId,
                                                  @RequestParam(defaultValue = "0", required = false) int pageSize,
                                                  @RequestParam(defaultValue = "20", required = false) int pageNum) {
        return ticketService.getBookedTicketsByEventId(eventId, pageSize, pageNum);
    }

    @PostMapping("/createTicket")
    public String createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.createTicket(ticketDTO);
    }

    @PostMapping("/bookTicket")
    public String bookTicket(@RequestParam int userId,
                             @RequestParam long eventId,
                             @RequestParam long address,
                             @RequestParam Ticket.Categories categories,
                             @RequestParam int amount) {
        return ticketService.bookTicket(userId, eventId, address, categories, amount);
    }

    @DeleteMapping("/cancelTicket")
    public String cancelTicket(@RequestParam long eventId,
                               @RequestParam int userId,
                               @RequestParam long addressId,
                               @RequestParam Ticket.Categories categories) {
        return ticketService.cancelTicket(userId, eventId, addressId, categories);
    }
}

/**
 * Ticket bookTicket(long userId, long eventId, int address, Ticket.Categories category);
 * List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);
 * List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);
 * boolean cancelTicket(long ticketId);
 */
