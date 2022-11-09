package com.epam.cdp.m2.hw2.aggregator.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.epam.cdp.m2.hw2.aggregator.frequency.impl.Java8AggregatorFrequencyTest;
import com.epam.cdp.m2.hw2.aggregator.duplicates.impl.Java8AggregatorDuplicatesTest;
import com.epam.cdp.m2.hw2.aggregator.sum.impl.Java8AggregatorSumTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Java8AggregatorFrequencyTest.class,
        Java8AggregatorSumTest.class,
        Java8AggregatorDuplicatesTest.class,
})
public class TestJava8Aggregator {
}
