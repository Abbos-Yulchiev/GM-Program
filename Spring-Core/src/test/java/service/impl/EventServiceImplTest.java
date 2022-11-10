package service.impl;

import entity.Event;
import entity.dto.EventDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repository.EventRepository;
import repository.impl.EventRepositoryImpl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(locations = {"/spring.xml"})
@ExtendWith(SpringExtension.class)
class EventServiceImplTest {

    @Mock
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventServiceImpl eventServiceImpl;


    @Test
    void testConstructor() {
        new EventServiceImpl(new EventRepositoryImpl(mock(JdbcTemplate.class)));
    }




    @Test
    void testGetEventsByTitle() {
        ResponseEntity<List<Event>> actualEventsByTitle = eventServiceImpl.getEventsByTitle("Dr", 3, 10);
        assertTrue(actualEventsByTitle.hasBody());
        assertEquals(HttpStatus.OK, actualEventsByTitle.getStatusCode());
        assertTrue(actualEventsByTitle.getHeaders().isEmpty());
    }



    @Test
    void testCreateEvent() throws DataAccessException {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        doNothing().when(jdbcTemplate).execute((String) any());
        EventServiceImpl eventServiceImpl = new EventServiceImpl(new EventRepositoryImpl(jdbcTemplate));

        EventDTO eventDTO = new EventDTO();
        eventDTO.setEvent_date("2020-03-01");
        eventDTO.setId(123L);
        eventDTO.setTitle("Dr");
        ResponseEntity<String> actualCreateEventResult = eventServiceImpl.createEvent(eventDTO);
        assertEquals("A new event successfully created", actualCreateEventResult.getBody());
        assertEquals(HttpStatus.OK, actualCreateEventResult.getStatusCode());
        assertTrue(actualCreateEventResult.getHeaders().isEmpty());
        verify(jdbcTemplate).execute((String) any());
    }


    @Test
    void testUpdateEvent() {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEvent_date("2020-03-01");
        eventDTO.setId(123L);
        eventDTO.setTitle("Dr");
        ResponseEntity<String> actualUpdateEventResult = eventServiceImpl.updateEvent(1, eventDTO);
        assertEquals("Event updated id=[1]", actualUpdateEventResult.getBody());
        assertEquals(HttpStatus.OK, actualUpdateEventResult.getStatusCode());
        assertTrue(actualUpdateEventResult.getHeaders().isEmpty());
    }


    @Test
    void testDeleteEvent() {
        ResponseEntity<String> actualDeleteEventResult = eventServiceImpl.deleteEvent(123L);
        assertEquals("Event deleted id=[123]", actualDeleteEventResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteEventResult.getStatusCode());
        assertTrue(actualDeleteEventResult.getHeaders().isEmpty());
    }
}

