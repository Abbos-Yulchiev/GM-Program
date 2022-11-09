package benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@State(Scope.Benchmark)
public class ConcurrencyIssue {

    private Random random = new Random();

    private List<String> config = Collections.synchronizedList(new ArrayList<String>() {
        {
            add("x");
        }
    });

    @GroupThreads(7)
    @Group("race")
    @Benchmark
    public String benchRead() {
        return config.get(0);
    }

    @GroupThreads(1)
    @Group("race")
    @Benchmark()
    public String benchWrite() {

        config.remove(0);
        config.add(0, Integer.toString(random.nextInt()));

        return config.get(0);

    }
}
