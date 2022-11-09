package org.example;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class LRUCacheServiceTest {

    @Test
    public void testConstructor() {
        assertTrue((new LRUCacheService()).isEmpty());
        assertTrue((new LRUCacheService()).isEmpty());
    }

    @Test
    public void testPut() {
        assertEquals("word", (new LRUCacheService()).put("word"));
        assertEquals("there we might write anything", (new LRUCacheService()).put("there we might write anything"));
    }

    @Test
    public void testGet() {
        assertNull((new LRUCacheService()).get("word"));
    }

    @Test
    public void testGetAll() {
        assertTrue((new LRUCacheService()).getAll().isEmpty());
    }

    @Test
    public void testGetAllEntrySet() {
        assertTrue((new LRUCacheService()).getAllEntrySet().isEmpty());
    }

    @Test
    public void testSize() {
        LFUCacheService service = new LFUCacheService();
        service.put("coding");
        service.put("programming");
        service.put("thoughts");
        service.put("where");
        assertEquals(4, service.size());

        //After putting the element into cache the size of the Cache is not changed
        service.put("where");
        assertEquals(4, service.size());
    }
}

