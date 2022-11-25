package demo.com.service;

import demo.com.entity.Event;
import demo.com.entity.dto.EventDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    Event getEvent(long id);

    List<Event> getAllEvents(int page, int pageSize);

    List<Event> getAllEventsByTitle(String name);

    Event createEvent(EventDTO eventDTO);

    Event updateEvent(long id, EventDTO eventDTO);

    String deleteEvent(long id);
}
