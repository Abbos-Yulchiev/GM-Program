package benchmark.snippets;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class Snippet1 {


    @Setup
    public void setupMyBenchMark() {

    }

    @Benchmark
    public String[] bench() {
        return "crowd==devoxx".split("==");
    }

    @TearDown
    public void tearDownMyBenchMark() {
    }

}
