package org.example;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LRUCacheService extends TreeMap<String, LocalDateTime> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final transient TreeMap<String, LocalDateTime> map;
    private final int maxSize = 100_000;
    // inserted element's quantity
    private int capacity;

    private static final Logger logger = Logger.getLogger(LRUCacheService.class.getName());


    public LRUCacheService() {
        this.map = new TreeMap<>();
    }

    public LocalDateTime put(String key) {
        if (capacity > maxSize && !map.containsKey(key)) {
            HelperClass.sortByValue(map);
            logger.info("Removed: \"" + map.firstKey() + "\" Time: " + LocalDateTime.now());
            map.remove(map.firstKey());
        } else {
            capacity++;
        }
        LocalDateTime time = LocalDateTime.now();
        map.put(key, time);
        return time;
    }

    @Override
    public LocalDateTime get(Object key) {
        synchronized (map) {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                logger.log(Level.WARNING, "Interrupted!", e);
                Thread.currentThread().interrupt();
            }
            LocalDateTime time = map.get(key) == null ? null : LocalDateTime.now();
            if (time == null) return null;
            else map.put((String) key, time);
            return time;
        }
    }

    public Set<String> getAll() {
        return map.keySet();
    }

    public Set<Map.Entry<String, LocalDateTime>> getAllEntrySet() {
        return map.entrySet();
    }

    @Override
    public int size() {
        return map.size();
    }
}