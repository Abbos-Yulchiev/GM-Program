package org.example;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LFUCacheService extends TreeMap<String, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final transient TreeMap<String, Integer> map;
    private final int maxSize = 100_000;
    private int capacity;

    private static final Logger logger = Logger.getLogger(LFUCacheService.class.getName());

    public LFUCacheService() {
        this.map = new TreeMap<>();
    }


    public String put(String key) {
        if (capacity > maxSize && !map.containsKey(key)) {
            HelperClass.sortByValue(map);
            logger.info("Removed: \"" + map.firstKey() + "\" Time: " + LocalDateTime.now());
            map.remove(map.firstKey());
        } else {
            capacity++;
        }
        Integer used = map.get(key);
        map.put(key, used == null ? 0 : used);
        return key;
    }

    public Integer get(String key) {
        synchronized (map) {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                logger.log(Level.WARNING, "Interrupted!", e);
                Thread.currentThread().interrupt();
            }
            Integer used = map.get(key);
            if (used == null) return 0;
            else map.put(key, ++used);
            return used;
        }
    }

    public Set<String> getAll() {
        return map.keySet();
    }

    public Set<Map.Entry<String, Integer>> getAllEntrySet() {
        return map.entrySet();
    }

    @Override
    public int size() {
        return map.size();
    }
}
