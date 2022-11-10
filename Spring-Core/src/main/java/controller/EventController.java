package controller;

import entity.Event;
import entity.dto.EventDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EventService;

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
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }


    @GetMapping("/getEventsByTitle/{title}")
    public ResponseEntity<List<Event>> getEventsByTitle(@RequestParam String title,
                                                        @RequestParam(defaultValue = "0", required = false) Integer pageSize,
                                                        @RequestParam(defaultValue = "20", required = false) Integer pageNum) {
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @GetMapping("/getEventsForDay/{title}")
    public ResponseEntity<List<Event>> getEventsForDay(@RequestParam String day,
                                                       @RequestParam(defaultValue = "0", required = false) Integer pageSize,
                                                       @RequestParam(defaultValue = "20", required = false) Integer pageNum)
            throws ParseException {
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }


    @PostMapping("/createEvent")
    public ResponseEntity<String> createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    @PutMapping("/updateEvent/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Integer id, @RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(id, eventDTO);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }
}
