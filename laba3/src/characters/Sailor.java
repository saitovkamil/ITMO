package characters;

import others.Person;

import java.util.Random;

public class Sailor extends Person {

    public Sailor(String name) {
        super(name, Role.Sailor);
    }

    public boolean repairHole() {
        Random random = new Random();
        return random.nextDouble() < 0.4;
    }

    public void listen() {
        System.out.println(this.getName() + " слушает приказы капитана");
    }

    @Override
    public void performAction() {
        System.out.println(this.getName() + " собирает все необходимые припасы для пути.");
        this.setPower(this.getPower() + 1);
    }

    @Override
    public void defend() {
        if (this.calculateStrength() > 5) {
            System.out.println(this.getName() + " слушает капитана и действует под его указаниями.");
        } else {
            System.out.println(this.getName() + " не слышит все указы капитана и начинает заниматься ерундой.");
            this.setIntellect(this.getIntellect() - 1);
            this.setFear(this.getFear() + 4);
        }

    }
}
