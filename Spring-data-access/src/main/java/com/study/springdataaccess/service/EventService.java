package com.study.springdataaccess.service;

import com.study.springdataaccess.entity.Event;
import com.study.springdataaccess.entity.dto.EventDTO;

import java.text.ParseException;
import java.util.List;

public interface EventService {

    Event getEventById(Long id);

    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    List<Event> getEventsForDay(String day, int pageSize, int pageNum) throws ParseException;

    String createEvent(EventDTO eventDTO);

    String updateEvent(long id, EventDTO eventDTO);

    String deleteEvent(Long id);
}
