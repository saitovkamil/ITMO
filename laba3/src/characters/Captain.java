package characters;

import errors.CaptiveException;
import others.Person;
import others.MainCharacter;

public class Captain extends Person implements MainCharacter {
    private int leadership;

    public Captain(String name) {
        super(name, Role.Captain);
        this.leadership = 10;
    }

    public Captain(String name, int power, boolean captive, int intellect, int fear, int leadership) {
        super(name, power, captive, intellect, fear);
        this.leadership = leadership;
    }

    @Override
    public void talk(Person robinzon) throws CaptiveException {
        if (this.getCaptive()) {
            throw new CaptiveException(this);
        }
        System.out.println("Капитан назначает " + robinzon.getName() + " управляющим второй шлюпки.");
        robinzon.setIntellect(getIntellect() + 10);
        robinzon.setFear(getFear() - 4);
    }

    public void preparation() {
        System.out.println(this.name + " приступает к снаряжению двух лодок");
        this.setPower(this.getPower() - 2);
    }

    @Override
    public void swim() {
        System.out.println("Шлюпка с капитаном отплывает");
        this.setPower(this.getPower() + 6);
    }

    @Override
    public void performAction() {
        if (this.leadership > 5) {
            System.out.println(this.getName() + " отдает четкие приказы, повышая мораль команды.");
            this.setFear(this.getFear() - 2);
            this.setPower(this.getPower() + 1);
        } else {
            System.out.println(this.getName() + " не может эффективно управлять командой.");
            this.setFear(this.getFear() + 2);
        }
    }

    @Override
    public void defend() {
        if (this.calculateStrength() > 5) {
            System.out.println(this.getName() + " подбадривает команду на атаку.");
            this.setLeadership(this.leadership + 1);
            this.setFear(this.getFear() - 3);
        } else {
            System.out.println(this.getName() + " решил не говорить команде о предстоящем бою.");
            this.setLeadership(this.leadership - 1);
            this.setFear(this.getFear() + 3);
        }
    }

    public int getLeadership() {
        return leadership;
    }

    public void setLeadership(int leadership) {
        this.leadership = leadership;
    }
}