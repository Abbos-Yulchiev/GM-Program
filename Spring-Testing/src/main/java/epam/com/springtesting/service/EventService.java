package epam.com.springtesting.service;

import epam.com.springtesting.entity.Event;
import epam.com.springtesting.entity.dto.EventDTO;

import java.util.List;

public interface EventService {

    Event getEventById(long id);

    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    List<Event> getEventsForDay(String day, int pageSize, int pageNum);

    String createEvent(EventDTO eventDTO);

    String updateEvent(long id, EventDTO eventDTO);

    String deleteEvent(long id);
}
