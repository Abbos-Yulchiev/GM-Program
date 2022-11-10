package mapper;

import entity.model.TicketEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketEventMapper implements RowMapper<TicketEvent> {

    @Override
    public TicketEvent mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        TicketEvent ticketEvent = new TicketEvent();
        ticketEvent.setId(resultSet.getLong(1));
        ticketEvent.setEventId(resultSet.getLong(2));
        ticketEvent.setTicketAmount(resultSet.getInt(3));
        ticketEvent.setSoldTickets(resultSet.getInt(4));
        return ticketEvent;
    }
}
