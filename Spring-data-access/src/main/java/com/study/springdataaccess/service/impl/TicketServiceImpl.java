package com.study.springdataaccess.service.impl;

import com.study.springdataaccess.entity.Ticket;
import com.study.springdataaccess.entity.UserAccount;
import com.study.springdataaccess.entity.dto.TicketDTO;
import com.study.springdataaccess.exceptions.InsufficientAmountException;
import com.study.springdataaccess.exceptions.InsufficientFoundException;
import com.study.springdataaccess.exceptions.NotFoundException;
import com.study.springdataaccess.repository.TicketRepository;
import com.study.springdataaccess.repository.UserAccountRepository;
import com.study.springdataaccess.service.TicketService;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final Logger LOGGER = Logger.getLogger(TicketServiceImpl.class);
    private final TicketRepository ticketRepository;
    private final UserAccountRepository userAccountRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, UserAccountRepository userAccountRepository) {
        this.ticketRepository = ticketRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<Ticket> getBookedTicketsByUser(long userId, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Ticket> bookedTickets = ticketRepository.getBookedTicketsByUser(userId, pageable);
        LOGGER.info(bookedTickets);
        return bookedTickets.toList();
    }

    @Override
    public List<Ticket> getBookedTicketsByEvent(long eventId, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Ticket> bookedTicketsByEvent = ticketRepository.getBookedTicketsByEvent(eventId, pageable);
        LOGGER.info(bookedTicketsByEvent);
        return bookedTicketsByEvent.toList();
    }

    @Override
    public String createTicket(TicketDTO ticketDTO) {

        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketDTO.getAmount(); i++) {
            Ticket ticket = new Ticket();
            ticket.setCategories(ticketDTO.getCategories());
            ticket.setEventId(ticketDTO.getEventId());
            ticket.setPlace(ticketDTO.getPlace());
            ticket.setPrice(ticket.getPrice());
            tickets.add(ticket);
        }
        ticketRepository.saveAll(tickets);
        LOGGER.info("Tickets are created.");
        return "Tickets are created.";
    }


    @Transactional()
    @Override
    public String bookTicket(long userId, long eventId, int place, Ticket.Categories categories, int amount) {

        List<Ticket> ticketAmount = ticketRepository.getTicketList(eventId, place, String.valueOf(categories), amount);
        if (amount > ticketAmount.size()) {
            LOGGER.error("Exception", new InsufficientAmountException("Ticket quantity (" + amount + ") is not enough"));
            throw new InsufficientAmountException(amount, "Ticket quantity (" + amount + ") is not enough");
        }

        UserAccount userAccountInfo = userAccountRepository.getUserAccountInfo(userId);
        double ticketPrice = ticketRepository.getTicketPrice(eventId, place);

        if (userAccountInfo.getBalance() < ticketPrice * amount) {
            LOGGER.error("Exception", new InsufficientAmountException("Your balance is not sufficient to buy. Check your balance"));
            throw new InsufficientFoundException("Your balance is not sufficient to buy. Check your balance");
        }

        for (Ticket ticket : ticketAmount) {
            ticket.setUserId(userId);
            ticket.setSold(true);
        }

        userAccountInfo.setBalance(userAccountInfo.getBalance() - ticketPrice * amount);
        userAccountRepository.save(userAccountInfo);
        ticketRepository.saveAll(ticketAmount);
        LOGGER.info("You've ordered [" + amount + "] ticket.");
        return "You've ordered [" + amount + "] ticket.";
    }
    //TODO    When calling service methods better would be an entity or model

    @Override
    public Boolean cancelTicket(long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Ticket not found with: id [" + id + "]"));
        ticket.setUserId(null);
        ticket.setCategories(null);
        ticket.setEventId(null);
        ticket.setPlace(0);
        ticketRepository.save(ticket);
        LOGGER.info("Ticket order canceled");
        return true;
    }
}
