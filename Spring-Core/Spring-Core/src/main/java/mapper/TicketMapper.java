package mapper;

import entity.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setEventId(resultSet.getLong(1));
        ticket.setUserId(resultSet.getInt(2));
        ticket.setEventId(resultSet.getLong(3));
        ticket.setCategories(Ticket.Categories.valueOf(resultSet.getString(4)));
        ticket.setPlace(resultSet.getString(5));
        ticket.setSold(resultSet.getBoolean(6));
        return ticket;
    }
}
