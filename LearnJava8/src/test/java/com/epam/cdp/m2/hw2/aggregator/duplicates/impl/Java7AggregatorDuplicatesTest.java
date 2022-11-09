package com.epam.cdp.m2.hw2.aggregator.duplicates.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Java7Aggregator;
import com.epam.cdp.m2.hw2.aggregator.duplicates.JavaAggregatorDuplicatesTest;

@RunWith(Parameterized.class)
public class Java7AggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java7AggregatorDuplicatesTest() {
        super(new Java7Aggregator());
    }
}
