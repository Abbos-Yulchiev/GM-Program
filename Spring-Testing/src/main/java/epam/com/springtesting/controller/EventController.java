package epam.com.springtesting.controller;

import epam.com.springtesting.entity.Event;
import epam.com.springtesting.entity.dto.EventDTO;
import epam.com.springtesting.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Done
 */
@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/getEventById/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }


    @GetMapping("/getEventsByTitle")
    public List<Event> getEventsByTitle(@RequestParam String title,
                                        @RequestParam(defaultValue = "0", required = false) int pageSize,
                                        @RequestParam(defaultValue = "20", required = false) int pageNum) {
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @GetMapping("/getEventsForDay")
    public List<Event> getEventsForDay(@RequestParam String day,
                                       @RequestParam(defaultValue = "0", required = false) int pageSize,
                                       @RequestParam(defaultValue = "20", required = false) int pageNum)
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
