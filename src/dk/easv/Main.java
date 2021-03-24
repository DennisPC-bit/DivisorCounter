package dk.easv;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {

        // Fetches the start time of the method.
        Instant start = Instant.now();
        int min = 1;
        int max = 100000;
        int threads =16;

        // Invokes the divisor counter
        ExecutorService es = Executors.newFixedThreadPool(threads);
        List<DivisorCounter> counters = new ArrayList<>();
        for(int i = 0; i<threads;i++)
            counters.add(new DivisorCounter(min+i*(max-min)/threads,(i+1)*(max-min)/threads));
        System.out.println("Looking for the best result...");
        es.invokeAll(counters);

        // Fetches the end time of the method.
        Instant end = Instant.now();

        // Find the highest result
        Result result = DivisorCounter.getBestResult();
        System.out.println(result.getNumber() + " maxResult " + result.getDivisorCounter() + " divisors!");
        System.out.println("Duration: " + Duration.between(start, end).toMillis() + " ms");
        es.shutdown();
    }
}
