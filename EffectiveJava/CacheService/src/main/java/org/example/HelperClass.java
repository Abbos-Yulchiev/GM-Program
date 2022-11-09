package org.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HelperClass {

    //This method used for sorting the map elements according to the value of the Map
    public static <T, V extends Comparable<? super V>> Map<T, V> sortByValue(Map<T, V> map) {
        List<Map.Entry<T, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<T, V> result = new LinkedHashMap<>();
        for (Map.Entry<T, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
