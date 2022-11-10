package repository.impl;

import entity.Event;
import entity.dto.EventDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(locations = {"/spring.xml"})
@RunWith(MockitoJUnitRunner.class)
public class EventRepositoryImplTest {
    @InjectMocks
    private EventRepositoryImpl eventRepositoryImpl;

    @Mock
    private JdbcTemplate jdbcTemplate;


    @Test
    public void testGetEventById() throws DataAccessException {
        Event event = new Event();
        event.setId(123L);
        LocalDateTime time = LocalDate.of(1970, 1, 1).atStartOfDay();
        event.setSetEventDate(Date.from(time.atZone(ZoneId.of("UTC")).toInstant()));
        event.setTitle("Dr");
        when(jdbcTemplate.queryForObject(any(), (RowMapper<Event>) any())).thenReturn(event);
        assertSame(event, eventRepositoryImpl.getEventById(123L));
        verify(jdbcTemplate).queryForObject(any(), (RowMapper<Event>) any());
    }


    @Test
    public void testGetEventsByTitle() {
        assertTrue(eventRepositoryImpl.getEventsByTitle("Dr", 3, 10).isEmpty());
    }


    @Test
    public void testGetEventsForDay() throws ParseException, DataAccessException {
        when(jdbcTemplate.queryForList((String) any())).thenReturn(new ArrayList<>());
        assertTrue(eventRepositoryImpl.getEventsForDay("Day", 3, 10).isEmpty());
        verify(jdbcTemplate).queryForList((String) any());
    }


    @Test
    public void testCreateEvent() throws DataAccessException {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        doNothing().when(jdbcTemplate).execute((String) any());
        EventRepositoryImpl eventRepositoryImpl = new EventRepositoryImpl(jdbcTemplate);

        EventDTO eventDTO = new EventDTO();
        eventDTO.setEvent_date("2020-03-01");
        eventDTO.setId(123L);
        eventDTO.setTitle("Dr");
        eventRepositoryImpl.createEvent(eventDTO);
        verify(jdbcTemplate).execute((String) any());
    }

    @Test
    public void testUpdateEvent() throws DataAccessException {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        doNothing().when(jdbcTemplate).execute((String) any());
        EventRepositoryImpl eventRepositoryImpl = new EventRepositoryImpl(jdbcTemplate);

        EventDTO eventDTO = new EventDTO();
        eventDTO.setEvent_date("2020-03-01");
        eventDTO.setId(123L);
        eventDTO.setTitle("Dr");
        eventRepositoryImpl.updateEvent(1, eventDTO);
        verify(jdbcTemplate).execute((String) any());
    }

    @Test
    public void testUpdateEvent2() throws DataAccessException {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        doNothing().when(jdbcTemplate).execute((String) any());
        EventRepositoryImpl eventRepositoryImpl = new EventRepositoryImpl(jdbcTemplate);

        EventDTO eventDTO = new EventDTO();
        eventDTO.setEvent_date(null);
        eventDTO.setId(123L);
        eventDTO.setTitle("Dr");
        eventRepositoryImpl.updateEvent(1, eventDTO);
        verify(jdbcTemplate).execute((String) any());
    }

    @Test
    public void testUpdateEvent3() throws DataAccessException {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        doNothing().when(jdbcTemplate).execute((String) any());
        EventRepositoryImpl eventRepositoryImpl = new EventRepositoryImpl(jdbcTemplate);

        EventDTO eventDTO = new EventDTO();
        eventDTO.setEvent_date("2020-03-01");
        eventDTO.setId(123L);
        eventDTO.setTitle(null);
        eventRepositoryImpl.updateEvent(1, eventDTO);
        verify(jdbcTemplate).execute((String) any());
    }

    @Test
    public void testDeleteEvent() throws DataAccessException {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        doNothing().when(jdbcTemplate).execute((String) any());
        (new EventRepositoryImpl(jdbcTemplate)).deleteEvent(123L);
        verify(jdbcTemplate).execute((String) any());
    }
}

