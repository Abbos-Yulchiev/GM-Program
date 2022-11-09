package repository;

import entity.Event;
import entity.dto.EventDTO;

import java.text.ParseException;
import java.util.List;

public interface EventRepository {

    Event getEventById(Long id);

    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    List<Event> getEventsForDay(String day, int pageSize, int pageNum) throws ParseException;

    void createEvent(EventDTO eventDTO);

    void updateEvent(int id, EventDTO eventDTO);

    void deleteEvent(Long id);
}
