package com.epam.cdp.m2.hw2.aggregator.duplicates.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Java8Aggregator;
import com.epam.cdp.m2.hw2.aggregator.duplicates.JavaAggregatorDuplicatesTest;

@RunWith(Parameterized.class)
public class Java8AggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java8AggregatorDuplicatesTest() {
        super(new Java8Aggregator());
    }
}

