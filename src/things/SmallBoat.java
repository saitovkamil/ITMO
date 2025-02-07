
package things;

import characters.Captain;
import characters.Robinzon;
import characters.Sailor;
import characters.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import errors.CaptiveException;

public class SmallBoat {
    public String name;
    public List<Sailor> sailors;

    public SmallBoat(String name) {
        this.name = name;
        this.sailors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    public List<Sailor> getSailors() {
        return sailors;
    }

    public void returnToBoat() {
        System.out.println("Моряки на лодке " + name + " вернулись на свои лодки.");
    }

    public void handleBoatTwo(SmallBoat boat2, Sailor sailor, Equipment equipment, Captain captain, Robinzon robinzon, Helper helper) throws CaptiveException {
        Random random = new Random();
        if (random.nextDouble() < 1 - equipment.getState()) {
            System.out.println(sailor.getName() + " подзывает всех и говорит, что на лодке " + boat2.getName() + " появилась дырка!");
            repairHole(boat2, sailor, captain, helper);
        } else {
            System.out.println(sailor.getName() + " проверяет все и видит, что на лодке " + boat2.getName() + " нет дырки.");
            System.out.println("Бунтари догоняют лодку и берут всех в плен!");
            captain.setCaptive(true);
            robinzon.setCaptive(true);
            sailor.setCaptive(true);
            helper.setCaptive(true);
        }
    }

    public void repairHole(SmallBoat boat, Sailor sailor, Captain captain, Helper helper) {

        if (sailor.repairHole()) {
            System.out.println(sailor.getName() + " успешно починил дырку на лодке " + boat.getName());
        } else {
            System.out.println(sailor.getName() + " не смог починить дырку на лодке " + boat.getName());
            System.out.println("На помощь приходят бунтари, они спасают их жизни, но берут в плен всех.");
            captain.setCaptive(true);
            helper.setCaptive(true);
        }
    }
}




