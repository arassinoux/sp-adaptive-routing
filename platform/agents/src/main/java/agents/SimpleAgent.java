package agents;

import agents.algorithms.BanditAlgorithm;

public class SimpleAgent extends AbstractAgent {

    private int step;

    public SimpleAgent(BanditAlgorithm banditAlgorithm) {

        super(banditAlgorithm, -1);

        step = 0;

    }

    public int selectArm()
    {
        int selectedArm = banditAlgorithm.selectArm();

        return selectedArm;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
           }

    @Override
    public void process(int selectedArm, double reward) {

        banditAlgorithm.update(selectedArm, reward);


        //System.out.println(Arrays.toString(cumulativeRegret));

    }


    public void finish() {

        System.out.println("agents.Agent is finishing...");

    }

    @Override
    public void reset() {
        super.reset();
        step = 0;
    }
}
