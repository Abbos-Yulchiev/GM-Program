package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;

/**
 * @author Abbos Yulchiev
 * @since 05-sep-2022
 */
public class Java7Aggregator implements Aggregator {


    /**
     * Done
     *
     * @param numbers list of integers to sum up
     * @return sum
     */
    @Override
    public int sum(List<Integer> numbers) {

        long startTime = System.currentTimeMillis();
        int sum = 0;
        for (int num : numbers)
            sum += num;

        long finishTime = System.currentTimeMillis();
        System.out.println("Java7Aggregator sum() method took: " + (finishTime - startTime) + "ms");
        return sum;
    }


    /**
     * Done
     *
     * @param words list of words
     * @param limit use this parameter to control the number of elements to return
     * @return words along with their frequencies
     * * (the output is sorted by frequency in descending order,
     * * if frequencies are equal then sorted by words in alphabetical order)
     */
    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {

        long startTime = System.currentTimeMillis();
        List<Pair<String, Long>> listOfWord = new ArrayList<>();
        Map<String, Long> map = new TreeMap<>();

        Long value = 1L;
        for (String word : words) {
            word = word.toLowerCase();
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            }
            //add a new element if the elements' quantity does not reach to the limit of words
            else map.put(word, value);
        }

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            Pair<String, Long> pair = new Pair<>(entry.getKey(), entry.getValue());
            listOfWord.add(pair);
            if (listOfWord.size() == limit) break;
        }

        listOfWord.sort(new Comparator<Pair<String, Long>>() {
            @Override
            public int compare(Pair<String, Long> pairOne, Pair<String, Long> pairTwo) {
                if (pairOne.getValue() > pairTwo.getValue()) return -1;
                else if (pairOne.getValue().equals(pairTwo.getValue())) return 0;
                else return 1;
            }
        });

        long finishTime = System.currentTimeMillis();
        System.out.println("Java7Aggregator getMostFrequentWords() method took: " + (finishTime - startTime) + "ms");
        return listOfWord;
    }


    /**
     * Done
     *
     * @param words list of words
     * @param limit use this parameter to control the number of elements to return
     * @return List words that have duplicate in the upper case sorted by length in ascending order
     */
    @Override
    public List<String> getDuplicates(List<String> words, long limit) {

        long startTime = System.currentTimeMillis();
        List<String> wordList = new ArrayList<>();
        Map<String, Integer> map = new TreeMap<>(
                new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        if (s1.length() < s2.length()) {
                            return -1;
                        } else if (s1.length() > s2.length()) {
                            return 1;
                        } else {
                            return s1.compareTo(s2);
                        }
                    }
                });
        for (String word : words) {
            word = word.toUpperCase();
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else map.put(word, 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                wordList.add(entry.getKey());
            }
            if (wordList.size() == limit) break;
        }

        wordList.sort(new Comparator<String>() {
            @Override
            public int compare(String wordOne, String wordTwo) {
                return Integer.compare(wordOne.length(), wordTwo.length());
            }
        });

        long finishTime = System.currentTimeMillis();
        System.out.println("Java7Aggregator getDuplicate() method took: " + (finishTime - startTime) + "ms");
        return wordList;
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        System.out.println((new Java7Aggregator()).sum(nums));

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
        System.out.println((new Java7Aggregator()).getMostFrequentWords(list, 10));
        System.out.println((new Java7Aggregator()).getDuplicates(list , 10 ));
    }
}
