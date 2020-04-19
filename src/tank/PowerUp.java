package tank;

import java.util.ArrayList;
import java.util.Random;

public class PowerUp {

    private static ArrayList<String> powerUps;

    static {
        PowerUp.powerUps = new ArrayList<>();

        powerUps.add("Increase Damage by 120%");
        powerUps.add("Increase Damage by 140%");
        powerUps.add("Increase Lives");
        powerUps.add("Increase Health by 110%");
        powerUps.add("Increase Health by 130%");
    }

    public String randomPowerUp() {
        Random powerUp = new Random();
        int random = powerUp.nextInt(powerUps.size());
        return powerUps.remove(random);
    }
}
