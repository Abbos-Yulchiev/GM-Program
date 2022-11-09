package repository.impl;

import entity.Ticket;
import entity.model.TicketEvent;
import mapper.TicketEventMapper;
import mapper.TicketMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicketRepositoryImpl implements TicketRepository {

    private JdbcTemplate jdbcTemplate;

    public TicketRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Get all booked tickets for specified user. Tickets should be sorted by event date in descending order.
     */
    @Override
    public List<Ticket> getBookedTickets(int userId, int pageSize, int pageNum) {

        String sql = "SELECT * FROM ticket as t JOIN events as e ON t.eventId = e.id " +
                "WHERE t.userId = " + userId + " and t.sold = true ORDER BY e.event_date " +
                " LIMIT " + pageSize + " OFFSET " + pageNum * pageSize;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        List<Ticket> ticketList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Ticket ticket = new Ticket();
            ticket.setId((Long) map.get("id"));
            ticket.setUserId((Integer) map.get("userId"));
            ticket.setEventId((Long) map.get("eventId"));
            ticket.setCategories(Ticket.Categories.valueOf((String) map.get("categories")));
            ticket.setPlace((String) map.get("place"));
            ticket.setSold((Boolean) map.get("sold"));
            ticketList.add(ticket);
        }
        return ticketList;
    }

    /**
     * Get all booked tickets for specified event. Tickets should be sorted in by user email in ascending order.
     */
    @Override
    public List<Ticket> getBookedTickets(long eventId, int pageSize, int pageNum) {

        String sql = "SELECT * FROM ticket JOIN events on ticket.eventId = events.id WHERE eventId = " + eventId +
                " and ticket.sold = true ORDER BY events.event_date " +
                "LIMIT " + pageSize + " OFFSET " + pageNum * pageSize;

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        List<Ticket> ticketList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Ticket ticket = new Ticket();
            ticket.setId((Long) map.get("id"));
            ticket.setUserId((Integer) map.get("userId"));
            ticket.setEventId((Long) map.get("eventId"));
            ticket.setCategories(Ticket.Categories.valueOf((String) map.get("categories")));
            ticket.setPlace((String) map.get("place"));
            ticket.setSold((Boolean) map.get("sold"));
            ticketList.add(ticket);
        }
        return ticketList;
    }

    @Override
    public Ticket bookTicket(int userId, long eventId, int place, Ticket.Categories categories) {

        String sql = "SELECT * FROM event_ticket where EVENTID = " + eventId;
        TicketEvent ticketEvent = jdbcTemplate.queryForObject(sql, new TicketEventMapper());

        assert ticketEvent != null;
        if (ticketEvent.getTicketAmount() == ticketEvent.getSoldTickets())
            return new Ticket();


        String getTicketId = "SELECT id FROM TICKET ORDER BY id desc LIMIT 1";
        Long lastTicketId = jdbcTemplate.queryForObject(getTicketId, Long.class);

        String sql2 = "INSERT INTO ticket (id, userId, eventId, categories, place, sold) " +
                "VALUES (" + lastTicketId + 1 + ", " + userId + ", " +
                eventId + ", '" + categories + "', " + place + ", " + true + ");";
        jdbcTemplate.execute(sql2);

        String sql3 = "SELECT * FROM ticket WHERE id = " + lastTicketId + 1;
//        return new Ticket();
        return jdbcTemplate.queryForObject(sql3, new TicketMapper());
    }


    @Override
    public boolean cancelTicket(long ticketId) {
        String sql2 = "SELECT * FROM ticket WHERE id = " + ticketId;
        TicketMapper ticketMapper = jdbcTemplate.queryForObject(sql2, TicketMapper.class);
        System.out.println(ticketMapper);
        return false;
    }
}
