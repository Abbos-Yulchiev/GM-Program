package demo.com.impl;

import demo.com.entity.Event;
import demo.com.entity.dto.EventDTO;
import demo.com.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import demo.com.repository.EventServiceRepository;
import demo.com.service.EventService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventServiceRepository repository;
    private final ModelMapper mapper;

    public EventServiceImpl(EventServiceRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Event getEvent(long id) {
        return repository.findById(id).orElseThrow(()
                -> new NotFoundException("Invalid id, event not found with di [" + id + "]"));
    }

    @Override
    public List<Event> getAllEvents(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        return repository.findAll(pageable).toList();
    }

    @Override
    public List<Event> getAllEventsByTitle(String name) {
        return repository.getAllEventsByTitle(name);
    }

    @Override
    public Event createEvent(EventDTO eventDTO) {

        Event event = new Event();
        mapper.map(eventDTO, event);
        return repository.save(event);
    }

    @Override
    public Event updateEvent(long id, EventDTO eventDTO) {

        Event event = repository.findById(id).orElseThrow(()
                -> new NotFoundException("Invalid id, event not found with di [" + id + "]"));

        mapper.map(event, eventDTO);
        return repository.save(event);
    }

    @Override
    public String deleteEvent(long id) {
        repository.deleteById(id);
        return "Event deleted with id [" + id + "]";
    }
}
