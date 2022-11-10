package mapper;

import entity.Event;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class EventMapperTest extends TestCase {

    @Test
    public void testMapRow() throws SQLException {
        EventMapper eventMapper = new EventMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(anyInt())).thenReturn("String");
        when(resultSet.getDate(anyInt())).thenReturn(mock(Date.class));
        when(resultSet.getLong(anyInt())).thenReturn(1L);
        Event actualMapRowResult = eventMapper.mapRow(resultSet, 10);
        assertEquals(1L, actualMapRowResult.getId().longValue());
        assertEquals("String", actualMapRowResult.getTitle());
        verify(resultSet).getString(anyInt());
        verify(resultSet).getDate(anyInt());
        verify(resultSet).getLong(anyInt());
    }
}

