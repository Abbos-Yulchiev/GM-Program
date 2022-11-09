package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8Aggregator implements Aggregator {

    /**
     * Done
     *
     * @param numbers list of integers to sum up
     * @return sum
     */
    @Override
    public int sum(List<Integer> numbers) {
        long startTime = System.currentTimeMillis();

        int sum = numbers.stream().mapToInt(Integer::intValue).sum();

        long finishTime = System.currentTimeMillis();
        System.out.println("Java8Aggregator class's sum() method took: " + (finishTime - startTime) + "ms");

        return sum;

    }

    /**
     * Done
     *
     * @param words list of words
     * @param limit use this parameter to control the number of elements to return
     * @return words along with their frequencies
     * (the output is sorted by frequency in descending order,
     * if frequencies are equal then sorted by words in alphabetical order)
     */
    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {

        long startTime = System.currentTimeMillis();

        Map<String, Long> map = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        List<Pair<String, Long>> collect = map.entrySet()
                .stream()
//                .peek(System.out::println)
                .map(e -> new Pair<>(e.getKey(), e.getValue()))
                .sorted(Comparator.comparing(Pair::getKey))
                .sorted(Comparator.comparing(e -> -e.getValue()))
                .limit(limit)
                .collect(Collectors.toList());
        long finishTime = System.currentTimeMillis();
        System.out.println("Java8Aggregator class's getMostFrequentWords() method took: " + (finishTime - startTime) + "ms");
        return collect;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {

        long startTime = System.currentTimeMillis();

        List<String> collect = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .sorted(Comparator.comparing(e -> e.getKey().length()))
                .sorted(Comparator.comparing(e -> -e.getValue()))
                .map(Map.Entry::getKey)
                .limit(limit)
                .collect((Collectors.toList()));

        long finishTime = System.currentTimeMillis();
        System.out.println("Java8Aggregator class's getDuplicates() method took: " + (finishTime - startTime) + "ms");
        return collect;
    }

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        (new Java8Aggregator()).sum(nums);

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
        (new Java8Aggregator()).getMostFrequentWords(list, 2);
        (new Java8Aggregator()).getDuplicates(list, 10);
    }
}