package com.epam.cdp.m2.hw2.aggregator.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.epam.cdp.m2.hw2.aggregator.frequency.impl.Java7AggregatorFrequencyTest;
import com.epam.cdp.m2.hw2.aggregator.duplicates.impl.Java7AggregatorDuplicatesTest;
import com.epam.cdp.m2.hw2.aggregator.sum.impl.Java7AggregatorSumTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Java7AggregatorFrequencyTest.class,
        Java7AggregatorSumTest.class,
        Java7AggregatorDuplicatesTest.class,
})
public class TestJava7Aggregator {
}
