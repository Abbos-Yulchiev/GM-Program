package com.epam.cdp.m2.hw2.aggregator.frequency;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Aggregator;

import javafx.util.Pair;

public abstract class JavaAggregatorFrequencyTest {

    @Parameterized.Parameter(0)
    public long limit;

    @Parameterized.Parameter(1)
    public List<String> words;

    @Parameterized.Parameter(2)
    public List<Pair<String, Long>> expected;

    private Aggregator aggregator;

    public JavaAggregatorFrequencyTest(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{2, asList("f", "c", "b", "b", "b", "c"), asList(new Pair<>("b", 3L), new Pair<>("c", 2L))});
        data.add(new Object[]{2, asList("f", "f"), asList(new Pair<>("f", 2L))});
        data.add(new Object[]{2, asList("f"), asList(new Pair<>("f", 1L))});
        data.add(new Object[]{2, asList("a", "b", "b", "a"), asList(new Pair<>("a", 2L), new Pair<>("b", 2L))});
        data.add(new Object[]{2, Collections.emptyList(), Collections.emptyList()});
        return data;
    }

    @Test
    public void test() {
        List<Pair<String, Long>> actual = aggregator.getMostFrequentWords(words, limit);
        assertEquals(expected, actual);
    }
}