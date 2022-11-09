package repository.impl;

import entity.Event;
import entity.dto.EventDTO;
import mapper.EventMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import repository.EventRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EventRepositoryImpl implements EventRepository {

    private JdbcTemplate jdbcTemplate;

    public EventRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Event getEventById(Long id) {

        String sql = "SELECT * FROM events WHERE id = " + id;
        return jdbcTemplate.queryForObject(sql, new EventMapper());
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {

        List<Map<String, Object>> eventMap = jdbcTemplate.queryForList("SELECT * FROM events WHERE title = '" + title + "' ORDER BY id LIMIT " + pageSize + " OFFSET " + pageNum * pageSize);
        List<Event> eventList = new ArrayList<>();
        for (Map<String, Object> map : eventMap) {
            Event event = new Event();
            event.setId((Long) map.get("id"));
            event.setTitle((String) map.get("title"));
            event.setSetEventDate((Date) map.get("event_date"));
            eventList.add(event);
        }
        return eventList;
    }

    @Override
    public List<Event> getEventsForDay(String day, int pageSize, int pageNum) throws ParseException {

        List<Map<String, Object>> eventMap = jdbcTemplate.queryForList("SELECT * FROM events WHERE event_date = '"
                + day + "' ORDER BY id LIMIT " + pageSize + " OFFSET " + pageNum * pageSize);

        List<Event> eventList = new ArrayList<>();
        for (Map<String, Object> map : eventMap) {
            Event event = new Event();
            event.setId((Long) map.get("id"));
            event.setTitle((String) map.get("title"));

            java.sql.Date date = (java.sql.Date) map.get("event_date");
            Date date1 = new SimpleDateFormat("yyyy-dd-MM").parse(String.valueOf(date));
            event.setSetEventDate(date1);
            eventList.add(event);
        }
        return eventList;
    }

    @Override
    public void createEvent(EventDTO eventDTO) {

        String sql = "INSERT INTO events (id, title, event_date) values ("
                + eventDTO.getId() + ", '" + eventDTO.getTitle() + "', '"
                + eventDTO.getEvent_date() + "')";
        jdbcTemplate.execute(sql);

    }

    @Override
    public void updateEvent(int id, EventDTO eventDTO) {

        String eventName = eventDTO.getTitle() == null ? "" : " title='" + eventDTO.getTitle() + "'";
        String eventDate = eventDTO.getEvent_date() == null ? "" : " event_date='" + eventDTO.getEvent_date() + "'";
        if (!eventName.equals("") && !eventDate.equals("")) eventName += ",";
        String sql = "UPDATE events SET " + eventName + eventDate + " WHERE id=" + id;
        jdbcTemplate.execute(sql);
    }

    @Override
    public void deleteEvent(Long id) {
        String sql = "DELETE FROM events WHERE id=" + id;
        jdbcTemplate.execute(sql);
    }
}
