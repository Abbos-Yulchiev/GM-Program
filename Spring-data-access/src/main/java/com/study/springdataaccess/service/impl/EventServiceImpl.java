package com.study.springdataaccess.service.impl;

import com.study.springdataaccess.entity.Event;
import com.study.springdataaccess.entity.dto.EventDTO;
import com.study.springdataaccess.exceptions.NotFoundException;
import com.study.springdataaccess.repository.EventRepository;
import com.study.springdataaccess.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Event not found with: id [" + id + "]"));
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Event> eventsForDay = eventRepository.getEventsForDay(title, pageable);
        return eventsForDay.toList();
    }

    @Override
    public List<Event> getEventsForDay(String day, int pageSize, int pageNum) throws ParseException {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Event> eventsForDay = eventRepository.getEventsForDay(day, pageable);
        return eventsForDay.toList();
    }

    @Override
    public String createEvent(EventDTO eventDTO) {

        Event event = new Event();
        mapper.map(eventDTO, event);
        Event save = eventRepository.save(event);
        return "A new event successfully created id: [" + save.getId() + "]";
    }

    @Override
    public String updateEvent(long id, EventDTO eventDTO) {

        Event event = eventRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Event not found with: id [" + id + "]"));
        mapper.map(eventDTO, event);
        return "Event updated id=[" + id + "]";
    }

    @Override
    public String deleteEvent(Long id) {
        eventRepository.deleteById(id);
        return "Event deleted id=[" + id + "]";
    }
}
