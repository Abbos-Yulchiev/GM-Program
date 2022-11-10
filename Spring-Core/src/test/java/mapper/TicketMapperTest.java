package mapper;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

public class TicketMapperTest {

    @Test
    public void testMapRow() throws SQLException {

        TicketMapper ticketMapper = new TicketMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.getString(anyInt())).thenReturn("BAR");
        when(resultSet.getLong(anyInt())).thenReturn(1L);
        ticketMapper.mapRow(resultSet, 6);
    }
}

