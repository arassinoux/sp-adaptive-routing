package arms;

import java.util.Random;

public class TriangularArm extends AbstractArm {

    private double a;
    private double b;
    private double c;

    public TriangularArm(int id, double a, double b, double c, int seed)
    {
        super(id, EnumArm.TRIANGULAR, seed);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getReward() {

        double F = (c - a) / (b - a);
        double rand = random.nextDouble();
        if (rand < F) {
            return a + Math.sqrt(rand * (b - a) * (c - a));
        } else {
            return b - Math.sqrt((1 - rand) * (b - a) * (b - c));
        }
    }

}
