package com.study.springdataaccess.controller;


import com.study.springdataaccess.entity.Ticket;
import com.study.springdataaccess.entity.dto.TicketDTO;
import com.study.springdataaccess.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/getBookedTicketsByUserId/{userId}")
    public List<Ticket> getBookedTicketsByUserId(@PathVariable long userId,
                                                 @RequestParam(defaultValue = "20", required = false) int pageSize,
                                                 @RequestParam(defaultValue = "0", required = false) int pageNum) {
        return ticketService.getBookedTicketsByUser(userId, pageSize, pageNum);
    }

    @GetMapping("/getBookedTicketsByEventId/{eventId}")
    public List<Ticket> getBookedTicketsByEventId(@PathVariable long eventId,
                                                  @RequestParam(defaultValue = "20", required = false) int pageSize,
                                                  @RequestParam(defaultValue = "0", required = false) int pageNum) {
        return ticketService.getBookedTicketsByEvent(eventId, pageSize, pageNum);
    }


    @PostMapping("/createTicket")
    public String createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.createTicket(ticketDTO);
    }

    @PostMapping("/bookTicket")
    public String bookTicket(@RequestParam int userId,
                             @RequestParam long eventId,
                             @RequestParam int place,
                             @RequestParam Ticket.Categories categories,
                             @RequestParam int amount) {
        return ticketService.bookTicket(userId, eventId, place, categories, amount);
    }

    @DeleteMapping("/cancelTicket/{id}")
    public Boolean cancelTicket(@PathVariable long id) {
        return ticketService.cancelTicket(id);
    }
}
