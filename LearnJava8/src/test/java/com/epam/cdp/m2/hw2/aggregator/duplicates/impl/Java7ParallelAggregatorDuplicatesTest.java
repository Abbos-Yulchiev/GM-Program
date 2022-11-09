package com.epam.cdp.m2.hw2.aggregator.duplicates.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Java7ParallelAggregator;
import com.epam.cdp.m2.hw2.aggregator.duplicates.JavaAggregatorDuplicatesTest;

@RunWith(Parameterized.class)
public class Java7ParallelAggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java7ParallelAggregatorDuplicatesTest() {
        super(new Java7ParallelAggregator());
    }
}
