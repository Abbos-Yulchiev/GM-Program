package com.study.springdataaccess.service.impl;

import com.study.springdataaccess.entity.Ticket;
import com.study.springdataaccess.entity.User;
import com.study.springdataaccess.entity.UserAccount;
import com.study.springdataaccess.entity.dto.TicketDTO;
import com.study.springdataaccess.exceptions.InsufficientAmountException;
import com.study.springdataaccess.exceptions.InsufficientFoundException;
import com.study.springdataaccess.exceptions.NotFoundException;
import com.study.springdataaccess.repository.TicketRepository;
import com.study.springdataaccess.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TicketServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TicketServiceImplTest {
    @MockBean
    private TicketRepository ticketRepository;

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    @MockBean
    private UserAccountRepository userAccountRepository;


    Ticket ticket = new Ticket();
    TicketDTO ticketDTO = new TicketDTO();
    User user = new User();
    UserAccount userAccount = new UserAccount();


    @BeforeEach
    private void init() {
        ticket.setCategories(Ticket.Categories.STANDARD);
        ticket.setEventId(123L);
        ticket.setId(123L);
        ticket.setPlace(1);
        ticket.setPrice(10.0d);
        ticket.setSold(true);
        ticket.setUserId(123L);

        ticketDTO.setAmount(10);
        ticketDTO.setCategories(Ticket.Categories.STANDARD);
        ticketDTO.setEventId(123L);
        ticketDTO.setPlace(1);

        user.setEmail("alex.santiago@email.org");
        user.setId(123L);
        user.setUsername("alex-santiago");

        userAccount.setAccountNumber(1234567890L);
        userAccount.setBalance(10.0d);
        userAccount.setId(123L);
        userAccount.setUser(user);
    }

    @Test
    void testGetBookedTicketsByUser() {
        when(ticketRepository.getBookedTicketsByUser(anyLong(), any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(ticketServiceImpl.getBookedTicketsByUser(123L, 3, 10).isEmpty());
        verify(ticketRepository).getBookedTicketsByUser(anyLong(), any());
    }

    @Test
    void testGetBookedTicketsByUser4() {
        when(ticketRepository.getBookedTicketsByUser(anyLong(), any()))
                .thenThrow(new InsufficientAmountException(123L));
        assertThrows(InsufficientAmountException.class, () -> ticketServiceImpl.getBookedTicketsByUser(123L, 3, 10));
        verify(ticketRepository).getBookedTicketsByUser(anyLong(), any());
    }

    @Test
    void testGetBookedTicketsByEvent() {
        when(ticketRepository.getBookedTicketsByEvent(anyLong(), any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(ticketServiceImpl.getBookedTicketsByEvent(123L, 3, 10).isEmpty());
        verify(ticketRepository).getBookedTicketsByEvent(anyLong(), any());
    }


    @Test
    void testGetBookedTicketsByEvent4() {
        when(ticketRepository.getBookedTicketsByEvent(anyLong(), any()))
                .thenThrow(new InsufficientAmountException(123L));
        assertThrows(InsufficientAmountException.class, () -> ticketServiceImpl.getBookedTicketsByEvent(123L, 3, 10));
        verify(ticketRepository).getBookedTicketsByEvent(anyLong(), any());
    }

    @Test
    void testCreateTicket() {
        when(ticketRepository.saveAll(any())).thenReturn(new ArrayList<>());

        assertEquals("Tickets are created.", ticketServiceImpl.createTicket(ticketDTO));
        verify(ticketRepository).saveAll(any());
    }


    @Test
    void testCreateTicket2() {
        when(ticketRepository.saveAll(any())).thenThrow(new InsufficientAmountException(123L));

        assertThrows(InsufficientAmountException.class, () -> ticketServiceImpl.createTicket(ticketDTO));
        verify(ticketRepository).saveAll(any());
    }


    @Test
    void testBookTicket() {
        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt())).thenReturn(new ArrayList<>());
        assertThrows(InsufficientAmountException.class,
                () -> ticketServiceImpl.bookTicket(123L, 123L, 1, Ticket.Categories.STANDARD, 10));
        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
    }


    @Test
    void testBookTicket2() {
        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt()))
                .thenReturn(new ArrayList<>());
        assertThrows(InsufficientAmountException.class,
                () -> ticketServiceImpl.bookTicket(123L, 123L, 1, Ticket.Categories.PREMIUM, 10));
        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
    }


    @Test
    void testBookTicket3() {
        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt()))
                .thenReturn(new ArrayList<>());
        assertThrows(InsufficientAmountException.class,
                () -> ticketServiceImpl.bookTicket(123L, 123L, 1, Ticket.Categories.BAR, 10));
        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
    }


    @Test
    void testBookTicket4() {
        when(ticketRepository.saveAll(any())).thenReturn(new ArrayList<>());
        when(ticketRepository.getTicketPrice(anyLong(), anyInt())).thenReturn(10.0d);
        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt()))
                .thenReturn(new ArrayList<>());

        User user1 = new User();
        user1.setEmail("alex.santiago@email.org");
        user1.setId(123L);
        user1.setUsername("alex-santiago");

        UserAccount userAccount1 = new UserAccount();
        userAccount1.setAccountNumber(1234567890L);
        userAccount1.setBalance(10.0d);
        userAccount1.setId(123L);
        userAccount1.setUser(user1);
        when(userAccountRepository.save(any())).thenReturn(userAccount1);
        when(userAccountRepository.getUserAccountInfo(anyLong())).thenReturn(userAccount);
        assertEquals("You've ordered [0] ticket.",
                ticketServiceImpl.bookTicket(123L, 123L, 1, Ticket.Categories.STANDARD, 0));
        verify(ticketRepository).getTicketPrice(anyLong(), anyInt());
        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
        verify(ticketRepository).saveAll(any());
        verify(userAccountRepository).getUserAccountInfo(anyLong());
        verify(userAccountRepository).save(any());
    }

    @Test
    void testCancelTicket() {
        Optional<Ticket> ofResult = Optional.of(ticket);

        Ticket ticket1 = new Ticket();
        ticket1.setCategories(Ticket.Categories.STANDARD);
        ticket1.setEventId(123L);
        ticket1.setId(123L);
        ticket1.setPlace(1);
        ticket1.setPrice(10.0d);
        ticket1.setSold(true);
        ticket1.setUserId(123L);
        when(ticketRepository.save(any())).thenReturn(ticket1);
        when(ticketRepository.findById(any())).thenReturn(ofResult);
        assertTrue(ticketServiceImpl.cancelTicket(123L));
        verify(ticketRepository).save(any());
        verify(ticketRepository).findById(any());
    }

    @Test
    void testCancelTicket2() {
        Optional<Ticket> ofResult = Optional.of(ticket);
        when(ticketRepository.save(any())).thenThrow(new InsufficientAmountException(123L));
        when(ticketRepository.findById(any())).thenReturn(ofResult);
        assertThrows(InsufficientAmountException.class, () -> ticketServiceImpl.cancelTicket(123L));
        verify(ticketRepository).save(any());
        verify(ticketRepository).findById(any());
    }


    @Test
    void testCancelTicket3() {
        when(ticketRepository.save(any())).thenReturn(ticket);
        when(ticketRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> ticketServiceImpl.cancelTicket(123L));
        verify(ticketRepository).findById(any());
    }
}

