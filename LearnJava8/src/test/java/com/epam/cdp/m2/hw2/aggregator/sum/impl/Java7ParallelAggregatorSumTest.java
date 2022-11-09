package com.epam.cdp.m2.hw2.aggregator.sum.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Java7ParallelAggregator;
import com.epam.cdp.m2.hw2.aggregator.sum.JavaAggregatorSumTest;

@RunWith(Parameterized.class)
public class Java7ParallelAggregatorSumTest extends JavaAggregatorSumTest {

    public Java7ParallelAggregatorSumTest() {
        super(new Java7ParallelAggregator());
    }
}
