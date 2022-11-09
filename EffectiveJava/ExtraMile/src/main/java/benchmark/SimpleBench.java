package benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.regex.Pattern;

@State(Scope.Benchmark)
public class SimpleBench {


    @Setup
    public void setupMyBenchMark() {

    }
    private String input = "crowd==devoxx";
    private Pattern pattern = Pattern.compile("==");

    @Benchmark
    public String[] benchOne() {

        return input.split("==", 2);
    }

    @Benchmark
    public String[] benchTwo() {

        return pattern.split(input, 2);
    }

    @TearDown
    public void tearDownMyBenchMark() {
    }

}
