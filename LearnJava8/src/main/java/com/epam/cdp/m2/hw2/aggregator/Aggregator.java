package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.List;


public interface Aggregator {

    /**
     * Use this method to find a sum of integers.
     *
     * @param numbers list of integers to sum up
     * @return sum
     */
    int sum(List<Integer> numbers); // simple method just to warm up :)

    /**
     * Use this method to find words frequency.
     *
     * @param words list of words
     * @param limit use this parameter to control the number of elements to return
     * @return words along with their frequencies
     * (the output is sorted by frequency in descending order,
     * if frequencies are equal then sorted by words in alphabetical order)
     */
    List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit);

    /**
     * Looks for duplicates (case insensitive, e.g. 'java' equals 'JaVA')
     *
     * @param words list of words
     * @param limit use this parameter to control the number of elements to return
     * @return list with the words that have duplicates in the upper case sorted by length in ascending order,
     * if length is the same, sorted alphabetically
     */
    List<String> getDuplicates(List<String> words, long limit);

}
