package com.epam.cdp.m2.hw2.aggregator.sum.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Java8ParallelAggregator;
import com.epam.cdp.m2.hw2.aggregator.sum.JavaAggregatorSumTest;

@RunWith(Parameterized.class)
public class Java8ParallelAggregatorSumTest extends JavaAggregatorSumTest {

    public Java8ParallelAggregatorSumTest() {
        super(new Java8ParallelAggregator());
    }
}
