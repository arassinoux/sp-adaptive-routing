package arms;

public class ArmFactory {

    public Arm createBernouilliArm(int id, double p) {
        return new BernouilliArm(id, p);
    }
}
