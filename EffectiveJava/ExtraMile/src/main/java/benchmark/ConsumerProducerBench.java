package benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.atomic.AtomicLong;

@State(Scope.Benchmark)
public class ConsumerProducerBench {

    private AtomicLong produce = new AtomicLong();

    private AtomicLong consume = new AtomicLong();

    @Group("consumeProduce")
    @GroupThreads(3)
    @Benchmark
    public void consume() {
        consume.incrementAndGet();
    }

    @Group("consumeProduce")
    @GroupThreads(1)
    @Benchmark
    public void produce() {
        produce.incrementAndGet();
    }

}
