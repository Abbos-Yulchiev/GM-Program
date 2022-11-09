package org.example;

import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LFUCacheServiceTest {


    @Test
    public void testConstructor() {
        assertTrue((new LFUCacheService()).isEmpty());
    }

    @Test
    public void testPut() {
        assertEquals("words", (new LFUCacheService()).put("words"));
    }


    @Test
    public void testGetSuccess() {
        LFUCacheService service = new LFUCacheService();
        service.put("word");
        /**
         * when get method is worked its value is increased by one
         */
        assertEquals(1, service.get("word").intValue());
        assertEquals(2, service.get("word").intValue());
        assertEquals(3, service.get("word").intValue());
    }

    @Test
    public void testGetForNotExistingKey() {
        assertEquals(0, (new LFUCacheService()).get("word").intValue());
    }

    @Test
    public void testGetAll() {
        assertTrue((new LFUCacheService()).getAll().isEmpty());
    }

    @Test
    public void testGetAllEntrySet() {

        assertTrue((new LFUCacheService()).getAllEntrySet().isEmpty());
//
//        LFUCacheService service = new LFUCacheService();
//
//        service.put("coding");
//        service.put("programming");
//        service.put("thoughts");
//        service.put("where");
//        assertEquals(new Set<Map.Entry>(), service.getAllEntrySet());
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

