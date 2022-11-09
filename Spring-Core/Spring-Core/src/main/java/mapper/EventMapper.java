package mapper;

import entity.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Event event = new Event();
        event.setId(resultSet.getLong(1));
        event.setTitle(resultSet.getString(2));
        event.setSetEventDate(resultSet.getDate(3));
        return event;
    }
}
