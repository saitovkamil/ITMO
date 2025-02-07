package things;

import characters.Sailor;
import errors.CaptiveException;
import others.Person;
import characters.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainShip {
    private int intellect;
    private int power;
    private int fear;
    private List<Person> crew;

    public MainShip() {
        this.crew = new ArrayList<>();
        this.intellect = 0;
        this.power = 0;
        this.fear = 0;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getFear() {
        return fear;
    }

    public void setFear(int fear) {
        this.fear = fear;
    }


    public void adjustCrewStats(SmallBoat boat2, Sailor s, Equipment equipment, Captain captain, Robinzon robinzon, Helper helper) throws CaptiveException {
        Random random = new Random();
        if (random.nextInt(18) > 15) {
            for (Person sailor : crew) {
                sailor.setFear(sailor.getFear() + 1);
                sailor.setPower(sailor.getPower() - 1);
            }
            System.out.println("Моряки видят численное преимущество бунатрей, пугаются и убегают.");
            boat2.returnToBoat();
            boat2.handleBoatTwo(boat2, s, equipment, captain, robinzon, helper);
//            boat2.repairHole(boat2, s);

        } else {
            for (Person sailor : crew) {
                sailor.setFear(sailor.getFear() - 1);
                sailor.setIntellect(sailor.getIntellect() + 1);
                sailor.setPower(sailor.getPower() + 1);
            }
            System.out.println("Моряки начали подавлять бунт.");
            int randInt = random.nextInt(4);
            switch (randInt){
                case 0:
                   captain.defend();
                   break;
                case 1:
                    helper.defend();
                    break;
                case 2:
                    robinzon.defend();
                    break;
                case 3:
                    s.defend();
                    break;
            }

        }
    }
}