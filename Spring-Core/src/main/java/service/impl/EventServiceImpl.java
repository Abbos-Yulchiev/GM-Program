package service.impl;

import entity.Event;
import entity.dto.EventDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import repository.EventRepository;
import service.EventService;

import java.text.ParseException;
import java.util.List;

public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ResponseEntity<Event> getEventById(Long id) {
        Event event = eventRepository.getEventById(id);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @Override
    public ResponseEntity<List<Event>> getEventsByTitle(String title, int pageSize, int pageNum) {
        List<Event> eventList = eventRepository.getEventsByTitle(title, pageSize, pageNum);
        return ResponseEntity.status(HttpStatus.OK).body(eventList);
    }

    @Override
    public ResponseEntity<List<Event>> getEventsForDay(String day, int pageSize, int pageNum) throws ParseException {
        List<Event> eventList = eventRepository.getEventsForDay(day, pageSize, pageNum);
        return ResponseEntity.status(HttpStatus.OK).body(eventList);
    }

    @Override
    public ResponseEntity<String> createEvent(EventDTO eventDTO) {
        eventRepository.createEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.OK).body("A new event successfully created");
    }

    @Override
    public ResponseEntity<String> updateEvent(int id, EventDTO eventDTO) {
        eventRepository.updateEvent(id, eventDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Event updated id=[" + id + "]");
    }

    @Override
    public ResponseEntity<String> deleteEvent(Long id) {
        eventRepository.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.OK).body("Event deleted id=[" + id + "]");
    }
}
