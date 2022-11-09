package com.epam.cdp.m2.hw2.aggregator.frequency.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Java8Aggregator;
import com.epam.cdp.m2.hw2.aggregator.frequency.JavaAggregatorFrequencyTest;

@RunWith(Parameterized.class)
public class Java8AggregatorFrequencyTest extends JavaAggregatorFrequencyTest {

    public Java8AggregatorFrequencyTest() {
        super(new Java8Aggregator());
    }
}

