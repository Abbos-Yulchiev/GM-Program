package mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"/spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.getString(anyInt())).thenReturn("String");
        User actualMapRowResult = userMapper.mapRow(resultSet, 10);
        assertEquals("String", actualMapRowResult.getEmail());
        assertEquals("String", actualMapRowResult.getUsername());
        assertEquals(1, actualMapRowResult.getId().intValue());
        verify(resultSet).getInt(anyInt());
        verify(resultSet, atLeast(1)).getString(anyInt());
    }
}

