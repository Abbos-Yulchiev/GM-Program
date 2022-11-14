package com.study.springdataaccess.service;

import com.study.springdataaccess.entity.Ticket;
import com.study.springdataaccess.entity.dto.TicketDTO;

import java.util.List;

public interface TicketService {


    List<Ticket> getBookedTicketsByUser(long userId, int pageSize, int pageNum);

    List<Ticket> getBookedTicketsByEvent(long eventId, int pageSize, int pageNum);

    String createTicket(TicketDTO ticketDTO);

    String bookTicket(long userId, long eventId, int place, Ticket.Categories categories, int amount);

    Boolean cancelTicket(long id);
}
