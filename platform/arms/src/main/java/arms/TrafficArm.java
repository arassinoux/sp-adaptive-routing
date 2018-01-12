package arms;

import arms.parameters.ArmParameters;

public class TrafficArm extends AbstractArm {

    private int requests;
    private int requestsLeft;

    TrafficArm(ArmParameters params)
    {
        super(EnumArm.TRAFFIC, params.getSeed());
        this.requests = 0;
        this.requestsLeft = 0;
    }


    public void setRequests(int requests) {
        this.requests = requests;
        //this.requestsLeft = requests;
    }

    @Override
    public double getReward() {
            //double value = 1.0 / (1.0 + ((double)requests - (double)requestsLeft));
            //requestsLeft = requestsLeft - 1;
            return 1.0 / (double)requests;
    }

    @Override
    public double expectedValue() {
        return 1;
    }

    public int getRequestsLeft() {
        return requestsLeft;
    }
}
