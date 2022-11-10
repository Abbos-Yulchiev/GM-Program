package service.impl;

import entity.Ticket;
import entity.model.TicketEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import repository.TicketRepository;
import repository.impl.TicketRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(locations = {"/spring.xml"})
@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;


    @Test
    void testConstructor() {
        new TicketServiceImpl(new TicketRepositoryImpl(mock(JdbcTemplate.class)));
    }


    @Test
    void testGetBookedTickets() {
        ResponseEntity<List<Ticket>> actualBookedTickets = ticketServiceImpl.getBookedTickets(123, 3, 10);
        assertTrue(actualBookedTickets.hasBody());
        assertEquals(HttpStatus.OK, actualBookedTickets.getStatusCode());
        assertTrue(actualBookedTickets.getHeaders().isEmpty());
    }


    @Test
    void testGetBookedTickets2() {
        ResponseEntity<List<Ticket>> actualBookedTickets = ticketServiceImpl.getBookedTickets(123, 3, 1);
        assertEquals(0, actualBookedTickets.getBody().size());
        assertTrue(actualBookedTickets.hasBody());
        assertEquals(HttpStatus.OK, actualBookedTickets.getStatusCode());
        assertTrue(actualBookedTickets.getHeaders().isEmpty());
    }


    @Test
    void testGetBookedTickets3() {
        ResponseEntity<List<Ticket>> actualBookedTickets = ticketServiceImpl.getBookedTickets(123L, 3, 10);
        assertTrue(actualBookedTickets.hasBody());
        assertEquals(HttpStatus.OK, actualBookedTickets.getStatusCode());
        assertTrue(actualBookedTickets.getHeaders().isEmpty());
    }


    @Test
    void testBookTicket() throws DataAccessException {
        TicketEvent ticketEvent = new TicketEvent();
        ticketEvent.setEventId(123L);
        ticketEvent.setId(123L);
        ticketEvent.setSoldTickets(1);
        ticketEvent.setTicketAmount(1);
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<TicketEvent>) any())).thenReturn(ticketEvent);
        ResponseEntity<Ticket> actualBookTicketResult = (new TicketServiceImpl(new TicketRepositoryImpl(jdbcTemplate)))
                .bookTicket(123, 123L, 1, Ticket.Categories.STANDARD);
        assertTrue(actualBookTicketResult.hasBody());
        assertTrue(actualBookTicketResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualBookTicketResult.getStatusCode());
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<TicketEvent>) any());
    }


    @Test
    void testCancelTicket() {
        when(ticketRepository.cancelTicket(anyLong())).thenReturn(true);
        ResponseEntity<Boolean> actualCancelTicketResult = ticketServiceImpl.cancelTicket(123L);
        assertTrue(actualCancelTicketResult.getBody());
        assertEquals(HttpStatus.OK, actualCancelTicketResult.getStatusCode());
        assertTrue(actualCancelTicketResult.getHeaders().isEmpty());
        verify(ticketRepository).cancelTicket(anyLong());
    }
}

