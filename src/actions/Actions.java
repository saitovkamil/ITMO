package actions;

import errors.CaptiveException;
import characters.*;
import things.Equipment;
import things.MainShip;
import things.SmallBoat;

public class Actions {
    public void startStory(Captain captain, Robinzon robinzon, Sailor sailor, Helper helper) throws CaptiveException {
        captain.performAction();
        captain.defend();
        captain.talk(robinzon);
        captain.preparation();
        System.out.println("Бригада приступает к заделке дыры на второй шлюпке.");
        sailor.listen();
        captain.swim();
        System.out.println("Они отбыли так удачно, что подошли к кораблю в полночь.");
    }

    public void rand(Story story, Captain captain, Robinzon robinzon, Helper helper) {
        story.characterStats(captain, robinzon, helper);
    }

    public void attack(Robinzon robinzon) {
        robinzon.command();
        System.out.println("Пока он болтал таким образом, шлюпка причалила к борту.");
    }

    public void sailorCount(MainShip mainship, SmallBoat boat2, Sailor s, Equipment equipment, Captain captain, Robinzon robinzon, Helper helper) throws CaptiveException {
        mainship.adjustCrewStats(boat2, s, equipment, captain, robinzon, helper);
    }

    public void startRiot(Story story,Helper helper, Captain captain, Robinzon robinzon, Sailor sailor) throws CaptiveException {
        story.riot(helper, captain, robinzon, sailor);
    }

    public void fire(Story story, Captain captain, Helper helper) throws CaptiveException {
        story.fireShip(captain, helper);
    }

    public void endStory(Story story, Captain captain, Robinzon robinzon, Helper helper) throws CaptiveException {
        story.end(captain,robinzon,helper);
    }
}
