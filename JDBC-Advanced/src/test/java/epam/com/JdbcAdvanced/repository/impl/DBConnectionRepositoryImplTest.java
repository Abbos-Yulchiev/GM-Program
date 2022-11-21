package epam.com.JdbcAdvanced.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import epam.com.JdbcAdvanced.model.dto.Carrier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DBConnectionRepositoryImpl.class})
@ExtendWith(SpringExtension.class)
class DBConnectionRepositoryImplTest {
    @Autowired
    private DBConnectionRepositoryImpl dBConnectionRepositoryImpl;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    void testAddTable() throws DataAccessException {
        doNothing().when(jdbcTemplate).execute((String) any());

        Carrier carrier = new Carrier();
        carrier.setEndPoint(1L);
        carrier.setName("TableOne");
        carrier.setStartingPoint(1L);
        assertEquals("Table created and row added with data", dBConnectionRepositoryImpl.addTable(carrier));
        verify(jdbcTemplate, atLeast(1)).execute((String) any());
    }

    @Test
    void testAddTable2() throws DataAccessException {
        doNothing().when(jdbcTemplate).execute((String) any());

        Carrier carrier = new Carrier();
        carrier.setEndPoint(Long.MAX_VALUE);
        carrier.setName("TableTwo");
        carrier.setStartingPoint(1L);
        assertEquals("Table created and row added with data", dBConnectionRepositoryImpl.addTable(carrier));
        verify(jdbcTemplate, atLeast(1)).execute((String) any());
    }

    @Test
    void testAddTable3() throws DataAccessException {
        doNothing().when(jdbcTemplate).execute((String) any());

        Carrier carrier = new Carrier();
        carrier.setEndPoint(1L);
        carrier.setName("Name");
        carrier.setStartingPoint(1L);
        assertEquals("Table created and row added with data", dBConnectionRepositoryImpl.addTable(carrier));
        verify(jdbcTemplate, atLeast(1)).execute((String) any());
    }

    @Test
    void testAddTable4() throws DataAccessException {
        doNothing().when(jdbcTemplate).execute((String) any());

        Carrier carrier = new Carrier();
        carrier.setEndPoint(Long.MAX_VALUE);
        carrier.setName("Name");
        carrier.setStartingPoint(1L);
        assertEquals("Table created and row added with data", dBConnectionRepositoryImpl.addTable(carrier));
        verify(jdbcTemplate, atLeast(1)).execute((String) any());
    }
}

