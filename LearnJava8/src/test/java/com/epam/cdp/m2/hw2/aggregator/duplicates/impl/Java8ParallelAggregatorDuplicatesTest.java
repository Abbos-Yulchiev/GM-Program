package com.epam.cdp.m2.hw2.aggregator.duplicates.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Java8ParallelAggregator;
import com.epam.cdp.m2.hw2.aggregator.duplicates.JavaAggregatorDuplicatesTest;

@RunWith(Parameterized.class)
public class Java8ParallelAggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java8ParallelAggregatorDuplicatesTest() {
        super(new Java8ParallelAggregator());
    }

}
