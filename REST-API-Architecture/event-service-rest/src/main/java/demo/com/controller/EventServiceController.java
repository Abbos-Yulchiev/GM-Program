package demo.com.controller;

import demo.com.entity.Event;
import demo.com.entity.dto.EventDTO;
import org.springframework.web.bind.annotation.*;
import demo.com.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eventService/")
public class EventServiceController {

    private final EventService eventService;

    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable long id) {
        return eventService.getEvent(id);
    }

    @GetMapping
    public List<Event> getEvents(@RequestParam(name = "pageNum", required = false, defaultValue = "0") int page,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return eventService.getAllEvents(page, pageSize);
    }

    @GetMapping("/name")
    public List<Event> getAllEventsByTitle(@RequestParam(name = "name") String name) {
        return eventService.getAllEventsByTitle(name);
    }

    @PostMapping
    public Event createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable long id, @RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(id, eventDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        return eventService.deleteEvent(id);
    }
}

