package service;

import entity.Event;
import entity.dto.EventDTO;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface EventService {

    ResponseEntity<Event> getEventById(Long id);

    ResponseEntity<List<Event>> getEventsByTitle(String title, int pageSize, int pageNum);

    ResponseEntity<List<Event>> getEventsForDay(String day, int pageSize, int pageNum) throws ParseException;

    ResponseEntity<String> createEvent(EventDTO eventDTO);

    ResponseEntity<String> updateEvent(int id, EventDTO eventDTO);

    ResponseEntity<String> deleteEvent(Long id);
}
