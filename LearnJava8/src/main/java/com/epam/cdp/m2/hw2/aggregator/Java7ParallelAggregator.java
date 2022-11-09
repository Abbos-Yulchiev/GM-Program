package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import javafx.util.Pair;

public class Java7ParallelAggregator implements Aggregator {

    /**
     * Done
     *
     * @param numbers list of integers to sum up
     * @return sum
     */

    @Override
    public int sum(List<Integer> numbers) {
        return ForkJoinPool.commonPool().invoke(new SumOfIntegerList(numbers));
    }

    /**
     * @param words list of words
     * @param limit use this parameter to control the number of elements to return
     * @return words along with their frequencies
     * (the output is sorted by frequency in descending order,
     * if frequencies are equal then sorted by words in alphabetical order)
     */
    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        return ForkJoinPool.commonPool().invoke(new GetFrequentWordsByParallelism(words, limit));
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        return ForkJoinPool.commonPool().invoke(new GetDuplicateWords(words, limit));
    }

    /**
     * Done
     * <p>
     * Fork Join for parallelism
     */
    private static class SumOfIntegerList extends RecursiveTask<Integer> {

        long startTime = System.currentTimeMillis();

        private List<Integer> integerList;

        private SumOfIntegerList(List<Integer> integerList) {
            this.integerList = integerList;
        }

        @Override
        protected Integer compute() {
            int sequentialThreshold = 2;
            if (integerList.size() <= sequentialThreshold) {
                return sumOfNumList();
            } else {
                int middle = integerList.size() / 2;
                // 2,3,4,5,       6,7,8,9
                List<Integer> newList = integerList.subList(middle, integerList.size());
                integerList = integerList.subList(0, middle);
                SumOfIntegerList task = new SumOfIntegerList(newList);
                task.fork();
                Integer sum1 = this.compute();
                Integer sum2 = task.join();
                return sum1 + sum2;
            }
        }


        private Integer sumOfNumList() {
            int sum = 0;
            for (int i : integerList) {
                sum += i;
            }
            long finishTime = System.currentTimeMillis();
            System.out.println("Java7ParallelAggregator sum() method took: " + (finishTime - startTime) + "ms");
            return sum;
        }
    }

    /**
     * Get frequency of a List of Strings
     */
    private static class GetFrequentWordsByParallelism extends RecursiveTask<List<Pair<String, Long>>> {


        long startTime = System.currentTimeMillis();
        private List<String> listOfWords;
        private final long limit;

        public GetFrequentWordsByParallelism(List<String> listOfWords, long limit) {
            this.listOfWords = listOfWords;
            this.limit = limit;
        }

        @Override
        protected List<Pair<String, Long>> compute() {

            List<Pair<String, Long>> wordsPair;
            int sequentialThreshold = 5;

            if (listOfWords.size() <= sequentialThreshold) {
                wordsPair = counter();
            } else {
                int middle = listOfWords.size() / 2;
                List<String> newList = listOfWords.subList(middle, listOfWords.size());
                listOfWords = listOfWords.subList(0, middle);
                GetFrequentWordsByParallelism task = new GetFrequentWordsByParallelism(newList, limit / 2);
                task.fork();
                List<Pair<String, Long>> resultOne = this.compute();
                List<Pair<String, Long>> resultTwo = task.join();
                resultOne.addAll(resultTwo);
                wordsPair = resultOne;
            }
            wordsPair.sort(new Comparator<Pair<String, Long>>() {
                @Override
                public int compare(Pair<String, Long> pairOne, Pair<String, Long> pairTwo) {
                    if (pairOne.getValue() > pairTwo.getValue()) return -1;
                    else if (pairOne.getValue().equals(pairTwo.getValue())) return 0;
                    else return 1;
                }
            });
            return wordsPair;
        }

        private List<Pair<String, Long>> counter() {

            List<Pair<String, Long>> pairOfWords = Collections.synchronizedList(new ArrayList<Pair<String, Long>>());
            Map<String, Long> map = new TreeMap<>();
            Long value = 1L;
            for (String word : listOfWords) {
                word = word.toLowerCase();
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                }
                //add a new element if the elements' quantity does not reach to the limit of words
                else map.put(word, value);
            }

            for (Map.Entry<String, Long> entry : map.entrySet()) {
                Pair<String, Long> pair = new Pair<>(entry.getKey(), entry.getValue());
                pairOfWords.add(pair);
                if (pairOfWords.size() == limit) break;
            }

            long finishTime = System.currentTimeMillis();
            System.out.println("Java7ParallelAggregator class's getMostFrequentWords() method took: " + (finishTime - startTime) + "ms");
            return pairOfWords;
        }
    }

    private static class GetDuplicateWords extends RecursiveTask<List<String>> {

        long startTime = System.currentTimeMillis();
        private List<String> wordList;
        private final long limit;

        private GetDuplicateWords(List<String> wordList, long limit) {
            this.wordList = wordList;
            this.limit = limit;
        }

        @Override
        protected List<String> compute() {

            List<String> words;
            int sequentialThreshold = 5;
            if (wordList.size() < sequentialThreshold) {
                words = getDuplicates();
            } else {
                int middle = wordList.size() / 2;
                List<String> newList = wordList.subList(middle, wordList.size());
                wordList = wordList.subList(0, middle);
                GetDuplicateWords task = new GetDuplicateWords(newList, limit / 2);
                task.fork();
                List<String> duplicates = this.getDuplicates();
                List<String> joined = task.join();
                duplicates.addAll(joined);
                words = duplicates;

            }
            words.sort(new Comparator<String>() {
                @Override
                public int compare(String wordOne, String wordTwo) {
                    return Integer.compare(wordTwo.length(), wordOne.length());
                }
            });
            return words;
        }

        private List<String> getDuplicates() {

            List<String> listOfWords = Collections.synchronizedList(new ArrayList<String>());
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
                    }
            );

            for (String word : wordList) {
                word = word.toUpperCase();
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else map.put(word, 1);
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() > 1)
                    listOfWords.add(entry.getKey());
                if (wordList.size() == limit) break;
            }

            long finishTime = System.currentTimeMillis();
            System.out.println("Java7ParallelAggregator class's getDuplicates() method took: " + (finishTime - startTime) + "ms");
            return listOfWords;
        }
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        System.out.println((new Java7ParallelAggregator()).sum(nums));

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
        System.out.println((new Java7ParallelAggregator()).getMostFrequentWords(list, 10));
        System.out.println((new Java7Aggregator()).getDuplicates(list, 10));
    }
}

