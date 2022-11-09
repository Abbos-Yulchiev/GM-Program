package epam.com.springtesting.service.impl;

import epam.com.springtesting.entity.Event;
import epam.com.springtesting.entity.dto.EventDTO;
import epam.com.springtesting.exceptions.NotFoundException;
import epam.com.springtesting.repository.EventRepository;
import epam.com.springtesting.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper mapper;


    public EventServiceImpl(EventRepository eventRepository, ModelMapper mapper) {
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    @Override
    public Event getEventById(long id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found! ID:[" + id + "]"));
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Event> allByTitle = eventRepository.findByTitle(title, pageable);
        return allByTitle.toList();
    }

    @Override
    public List<Event> getEventsForDay(String day, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Event> allByDate = eventRepository.findByDate(Timestamp.valueOf(day), pageable);
        return allByDate.toList();
    }

    @Override
    public String createEvent(EventDTO eventDTO) {
        Event event = mapper.map(eventDTO, Event.class);
        Long id = eventRepository.save(event).getId();
        return "New event created. Id:[" + id + "]";
    }

    @Override
    public String updateEvent(long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Event not found! Id:[" + id + "]"));
        mapper.map(eventDTO, event);
        eventRepository.save(event);
        return "Event updated. id:[" + id + "]";
    }

    @Override
    public String deleteEvent(long id) {
        eventRepository.deleteById(id);
        return "Event deleted. Id:[" + id + "]";
    }
}
