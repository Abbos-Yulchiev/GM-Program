package com.epam.cdp.m2.hw2.aggregator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class Java8ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {

        long startTime = System.currentTimeMillis();
        int sum = numbers.parallelStream().mapToInt(Integer::intValue).sum();
        long finishTime = System.currentTimeMillis();
        System.out.println("Java8ParallelAggregator class's getMostFrequentWords() method took: " + (finishTime - startTime) + "ms");
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {

        long startTime = System.currentTimeMillis();

        Map<String, Long> map = words.parallelStream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Pair<String, Long>> collect = map.entrySet()
                .parallelStream()
                .map(e -> new Pair<>(e.getKey(), e.getValue()))
                .sorted(Comparator.comparing(Pair::getKey))
                .sorted(Comparator.comparing(e -> -e.getValue()))
                .limit(limit)
                .collect(Collectors.toList());
        long finishTime = System.currentTimeMillis();
        System.out.println("Java8ParallelAggregator class's getMostFrequentWords() method took: " + (finishTime - startTime) + "ms");
        return collect;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {

        long startTime = System.currentTimeMillis();

        List<String> collect = words.parallelStream()
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .parallelStream()
                .filter(e -> e.getValue() > 1)
                .sorted(Comparator.comparing(e -> -e.getValue()))
                .sorted(Comparator.comparing(e -> e.getKey().length()))
                .map(Map.Entry::getKey)
                .limit(limit)
                .collect(Collectors.toList());

        long finishTime = System.currentTimeMillis();
        System.out.println("Java8ParallelAggregator class's getDuplicates() method took: " + (finishTime - startTime) + "ms");
        return collect;
    }

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        System.out.println((new Java8ParallelAggregator()).sum(nums));

        List<String> list = new ArrayList<>();
        list.add("Abccc");
        list.add("abccc");
        list.add("abcdddd");
        list.add("abcdddd");
        list.add("aaaaa");
        list.add("aaaaa");
        list.add("aaaaa");
        list.add("aaaaa");
        list.add("bbbbb");
        System.out.println((new Java8ParallelAggregator()).getMostFrequentWords(list, 10));
        System.out.println((new Java8ParallelAggregator()).getDuplicates(list, 10));
    }

}
