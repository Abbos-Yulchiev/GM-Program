package epam.com.springtesting.service.impl;

import epam.com.springtesting.entity.Ticket;
import epam.com.springtesting.entity.dto.TicketDTO;
import epam.com.springtesting.exceptions.InsufficientAmountException;
import epam.com.springtesting.repository.TicketRepository;
import epam.com.springtesting.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             KafkaTemplate<String, String> kafkaTemplate) {
        this.ticketRepository = ticketRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public List<Ticket> getBookedTicketsByUserId(int userId, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Ticket> ticketPage = ticketRepository.findByUser(userId, pageable);
        return ticketPage.toList();
    }

    @Override
    public List<Ticket> getBookedTicketsByEventId(long eventId, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Ticket> ticketPage = ticketRepository.findByEvent(eventId, pageable);
        return ticketPage.toList();
    }


    @Override
    public String createTicket(TicketDTO ticketDTO) {
        List<Ticket> ticketList = new ArrayList<>();
        for (int i = 0; i < ticketDTO.getCount(); i++) {
            ticketList.add(new Ticket(ticketDTO.getEventId(),
                    ticketDTO.getCategories(),
                    ticketDTO.getAddress(),
                    ticketDTO.getPrice()));
        }
        ticketRepository.saveAll(ticketList);
        return "New [" + ticketDTO.getCount() + "] ticket(s) are created";
    }

    @Async
    @Override
    public String bookTicket(int userId, long eventId, long address, Ticket.Categories categories, int amount) {

        List<Ticket> ticketAmount = ticketRepository.getTicketList(eventId, address, String.valueOf(categories), amount);
        if (amount > ticketAmount.size())
            throw new InsufficientAmountException(amount, "Ticket quantity (" + amount + ") is not enough");
        for (Ticket ticket : ticketAmount) {
            ticket.setUserId(userId);
            ticket.setSold(true);
        }
        List<Ticket> ticketList = ticketRepository.saveAll(ticketAmount);
        for (Ticket ticket : ticketList) {
            kafkaTemplate.send("Order", ticket.toString());
        }
        return "You've ordered [" + amount + "] ticket.";
    }

    @Override
    public String cancelTicket(int userId, long eventId, long addressId, Ticket.Categories categories) {
        List<Ticket> ticketList = ticketRepository.cancelTicket(userId, eventId, addressId, String.valueOf(categories));
        for (Ticket ticket : ticketList) {
            ticket.setUserId(0);
            ticket.setSold(false);
        }
        ticketRepository.saveAll(ticketList);
        return "Ticket(s) canceled";
    }
}
