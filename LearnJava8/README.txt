Task description

Your main goal is to implement Aggregator interface. There are already four classes implementing the interface, but all their methods throw UnsupportedOperationException:

1.	Java7Aggregator – should use Java 7 approach to solve the problem (no packages from Java 8)
2.	Java8Aggregator – should use Java 8 Stream API to solve the problem
3.	Java8ParallelAggregator – should use parallel streams from Java 8 Stream API to solve the problem
4.	Java7ParallelAggregator -  use Java 7 approach to do parallel tasks (e.g. you may use fork/join framework) to solve the problem

If you run tests, you will see a lot of red. “What tests…?” , you may wonder. 
Yes, I wrote them for you, so now you are driven by tests (see 'How to run tests' section below for more details).
Aggregator interface has small javadocs to help you in understanding how each of the method behaves.

Evaluation
1.	Java7Aggregator – 4 points
2.	Java8Aggregator – 4 points
3.	Java8ParallelAggregator – 1 point
4.	Java7ParallelAggregator – 1 point (extra mile)
5.	Implement small performance tests and measure the average time each aggregator runs (tip: use larger data sets, than those used in my tests) 
Decide, which implementation is better (e.g. you may add small txt file with your conclusions) – 1 point (extra extra mile)

Total: 9 + 2 (extra miles) = 11 (points).


How to run tests

- Use 'com.epam.cdp.m2.hw2.aggregator.suits' package to run test by implementation;
- Use classes inside 'com.epam.cdp.m2.hw2.aggregator.[sum|duplicates|frequency].impl' package to test each separate method of each separate implementation.

good luck :)