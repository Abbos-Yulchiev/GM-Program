package mapper;

import entity.model.TicketEvent;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TicketEventMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        TicketEventMapper ticketEventMapper = new TicketEventMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.getLong(anyInt())).thenReturn(1L);
        TicketEvent actualMapRowResult = ticketEventMapper.mapRow(resultSet, 10);
        assert actualMapRowResult != null;
        assertEquals(1L, actualMapRowResult.getEventId());
        assertEquals(1, actualMapRowResult.getTicketAmount());
        assertEquals(1, actualMapRowResult.getSoldTickets());
        assertEquals(1L, actualMapRowResult.getId());
        verify(resultSet, atLeast(1)).getInt(anyInt());
        verify(resultSet, atLeast(1)).getLong(anyInt());
    }
}

