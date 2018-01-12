package agents.algorithms.utils;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<Integer> {

    private final NavigableMap<Double, Integer> map = new TreeMap<>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this.random = new Random();
    }


    public void add(double weight, Integer result) {
        if (weight <= 0) return;

        total += weight;
        map.put(total, result);
    }

    public Integer next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }
}
