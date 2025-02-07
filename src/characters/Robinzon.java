package characters;

import others.Person;

public class Robinzon extends Person {
    public Robinzon(String name) {
        super(name, Role.Robinzon);
    }

    // Метод command теперь изменяет уровень силы и интеллекта
    public void command() {
        System.out.println(this.getName() + " отдает приказ сесть по лодкам морякам и отправиться вслед.");
        this.setPower(this.getPower() + 1);
        this.setIntellect(this.getIntellect() + 2);
    }

    @Override
    public void performAction() {
        System.out.println(this.getName() + " готовится к отправлению.");
        this.setPower(this.getPower() + 1);
        this.setFear(this.getFear() - 1);
    }

    @Override
    public void defend() {
        if (this.calculateStrength() > 5) {
            System.out.println(this.getName() + " смело берет на себя главные задачи.");
            this.setPower(this.getPower() + 2);
            this.setFear(this.getFear() - 2);
        } else {
            System.out.println(this.getName() + " не может собраться и сливается с толпой.");
            this.setPower(this.getPower() - 1);
            this.setFear(this.getFear() + 1);
        }
    }
}