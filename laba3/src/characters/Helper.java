package characters;

import others.Person;

public class Helper extends Person {
    public Helper(String name) {
        super(name, Role.Helper);
    }

    public void helpCaptain() {
        System.out.println(this.getName() + " помогает капитану.");
    }

    @Override
    public void performAction() {
        System.out.println(this.getName() + " помогает остальной команде проверить шлюпки.");
        this.setPower(this.getPower() + 1);
    }

    @Override
    public void defend() {
        if (this.calculateStrength() > 5) {
            System.out.println(this.getName() + " качественно помогает остальным.");
            this.setPower(this.getPower() + 1);
            this.setIntellect(this.getIntellect() + 1);
        } else {
            System.out.println(this.getName() + " растерян и не может понять где пригодится его помощь");
            this.setIntellect(this.getIntellect() - 1);
            this.setFear(this.getFear() + 1);
        }

    }
}