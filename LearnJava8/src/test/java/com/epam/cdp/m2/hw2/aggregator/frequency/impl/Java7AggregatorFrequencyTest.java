package com.epam.cdp.m2.hw2.aggregator.frequency.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Java7Aggregator;
import com.epam.cdp.m2.hw2.aggregator.frequency.JavaAggregatorFrequencyTest;

@RunWith(Parameterized.class)
public class Java7AggregatorFrequencyTest extends JavaAggregatorFrequencyTest {

    public Java7AggregatorFrequencyTest() {
        super(new Java7Aggregator());
    }

}
