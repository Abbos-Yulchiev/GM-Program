package org.example;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        LFUCacheService map = new LFUCacheService();

        System.out.println(map.get("a"));

        map.put("a");
        map.put("b");
        map.put("d");
        map.put("f");
        System.out.println(map.get("a"));
        System.out.println(map.size());
        System.out.println(map.getAll());
        System.out.println(map.getAllEntrySet());

        LRUCacheService map2 = new LRUCacheService();
        map2.put("a");
        map2.put("b");
        map2.put("c");
        map2.put("d");
        map2.put("i");
        map2.put("f");

        System.out.println(map2.getAll());
        System.out.println(map2.get("a"));
        System.out.println(map2.getAllEntrySet());
        System.out.println(map2.getAll());
        System.out.println(map2.getAllEntrySet());
    }
}