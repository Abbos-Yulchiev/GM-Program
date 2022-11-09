package com.epam.cdp.m2.hw2.aggregator.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.epam.cdp.m2.hw2.aggregator.frequency.impl.Java7ParallelAggregatorFrequencyTest;
import com.epam.cdp.m2.hw2.aggregator.duplicates.impl.Java7ParallelAggregatorDuplicatesTest;
import com.epam.cdp.m2.hw2.aggregator.sum.impl.Java7ParallelAggregatorSumTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Java7ParallelAggregatorFrequencyTest.class,
        Java7ParallelAggregatorSumTest.class,
        Java7ParallelAggregatorDuplicatesTest.class,
})
public class TestJava7ParallelAggregator {
}
