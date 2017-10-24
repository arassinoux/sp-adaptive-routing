package algorithms.helpers;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<Arm> {

    private final NavigableMap<Double, Arm> map = new TreeMap<>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this.random = new Random();
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public void add(double weight, Arm result) {
        if (weight <= 0) return;

        total += weight;
        map.put(total, result);
    }

    public Arm next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }
}