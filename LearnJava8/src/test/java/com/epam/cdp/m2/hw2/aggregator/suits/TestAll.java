package com.epam.cdp.m2.hw2.aggregator.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.epam.cdp.m2.hw2.aggregator.frequency.impl.Java7AggregatorFrequencyTest;
import com.epam.cdp.m2.hw2.aggregator.frequency.impl.Java7ParallelAggregatorFrequencyTest;
import com.epam.cdp.m2.hw2.aggregator.frequency.impl.Java8AggregatorFrequencyTest;
import com.epam.cdp.m2.hw2.aggregator.frequency.impl.Java8ParallelAggregatorFrequencyTest;
import com.epam.cdp.m2.hw2.aggregator.duplicates.impl.Java7AggregatorDuplicatesTest;
import com.epam.cdp.m2.hw2.aggregator.duplicates.impl.Java7ParallelAggregatorDuplicatesTest;
import com.epam.cdp.m2.hw2.aggregator.duplicates.impl.Java8AggregatorDuplicatesTest;
import com.epam.cdp.m2.hw2.aggregator.duplicates.impl.Java8ParallelAggregatorDuplicatesTest;
import com.epam.cdp.m2.hw2.aggregator.sum.impl.Java7AggregatorSumTest;
import com.epam.cdp.m2.hw2.aggregator.sum.impl.Java7ParallelAggregatorSumTest;
import com.epam.cdp.m2.hw2.aggregator.sum.impl.Java8AggregatorSumTest;
import com.epam.cdp.m2.hw2.aggregator.sum.impl.Java8ParallelAggregatorSumTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Java7AggregatorFrequencyTest.class,
        Java7AggregatorSumTest.class,
        Java7AggregatorDuplicatesTest.class,

        Java8AggregatorFrequencyTest.class,
        Java8AggregatorSumTest.class,
        Java8AggregatorDuplicatesTest.class,

        Java7ParallelAggregatorFrequencyTest.class,
        Java7ParallelAggregatorSumTest.class,
        Java7ParallelAggregatorDuplicatesTest.class,

        Java8ParallelAggregatorFrequencyTest.class,
        Java8ParallelAggregatorSumTest.class,
        Java8ParallelAggregatorDuplicatesTest.class,
})
public class TestAll {
}
