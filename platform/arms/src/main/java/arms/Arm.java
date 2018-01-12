package arms;

public interface Arm {

    double getReward();
    double expectedValue();
    EnumArm getArmType();
    void setArmId(int armId);
    int getArmId();
}
