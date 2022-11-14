package com.study.springdataaccess.controller;

import com.study.springdataaccess.entity.Event;
import com.study.springdataaccess.entity.dto.EventDTO;
import com.study.springdataaccess.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Done
 */
@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/getEventById/{id}")
    public Event getEventById(@PathVariable long id) {
        return eventService.getEventById(id);
    }


    @GetMapping("/getEventsByTitle/{title}")
    public List<Event> getEventsByTitle(@RequestParam String title,
                                        @RequestParam(defaultValue = "0", required = false) Integer pageSize,
                                        @RequestParam(defaultValue = "20", required = false) Integer pageNum) {
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @GetMapping("/getEventsForDay/{title}")
    public List<Event> getEventsForDay(@RequestParam String day,
                                       @RequestParam(defaultValue = "0", required = false) Integer pageSize,
                                       @RequestParam(defaultValue = "20", required = false) Integer pageNum)
            throws ParseException {
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }


    @PostMapping("/createEvent")
    public String createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    @PutMapping("/updateEvent/{id}")
    public String updateEvent(@PathVariable long id, @RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(id, eventDTO);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable long id) {
        return eventService.deleteEvent(id);
    }
}
