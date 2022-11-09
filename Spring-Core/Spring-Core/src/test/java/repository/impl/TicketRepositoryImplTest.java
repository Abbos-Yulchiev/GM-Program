package repository.impl;

import entity.Ticket;
import entity.model.TicketEvent;
import mapper.TicketMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(locations = {"/spring.xml"})
@RunWith(MockitoJUnitRunner.class)
public class TicketRepositoryImplTest {

    @Mock
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TicketRepositoryImpl ticketRepositoryImpl;

    @Test
    public void testConstructor() {
        new TicketRepositoryImpl(mock(JdbcTemplate.class));
    }

    @Test
    public void testGetBookedTickets() {
        assertTrue(ticketRepositoryImpl.getBookedTickets(123, 3, 10).isEmpty());
        assertEquals(0, ticketRepositoryImpl.getBookedTickets(123, 1, 10).size());
        assertTrue(ticketRepositoryImpl.getBookedTickets(123L, 3, 10).isEmpty());
        assertEquals(0, ticketRepositoryImpl.getBookedTickets(1L, 1, 10).size());
    }


    @Test
    public void testBookTicket() throws DataAccessException {
        TicketEvent ticketEvent = new TicketEvent();
        ticketEvent.setEventId(123L);
        ticketEvent.setId(123L);
        ticketEvent.setSoldTickets(1);
        ticketEvent.setTicketAmount(1);
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<TicketEvent>) any())).thenReturn(ticketEvent);
        (new TicketRepositoryImpl(jdbcTemplate)).bookTicket(123, 123L, 1, Ticket.Categories.STANDARD);
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<TicketEvent>) any());
    }

    @Test
    public void testCancelTicket() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (Class<TicketMapper>) any())).thenReturn(new TicketMapper());
        assertFalse(ticketRepositoryImpl.cancelTicket(123L));
        verify(jdbcTemplate).queryForObject((String) any(), (Class<TicketMapper>) any());
    }
}

