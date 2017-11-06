import agents.Agent;
import agents.AgentFactory;
import algorithms.*;
import arms.ArmFactory;
import arms.EnumArm;
import scenarios.SimpleScenario;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        SimpleScenario scenario = new SimpleScenario(10);
        scenario.play();
    }

}