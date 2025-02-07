package others;

import java.util.Objects;

public abstract class Person {
    public String name;
    public int power;
    public boolean captive;
    public int intellect;
    public int fear;

    public Person(String name, int power, boolean captive, int intellect, int fear) {
        this.name = name;
        this.power = power;
        this.captive = captive;
        this.intellect = intellect;
        this.fear = fear;

    }

    public Person(String name, Role captain) {
        this.name = name;
        this.power = 0;
        this.captive = false;
        this.intellect = 0;
        this.fear = 0;
    }

    public Person(String name) {
        this.name = name;
        this.power = 0;
        this.captive = false;
        this.intellect = 0;
        this.fear = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean getCaptive() {
        return this.captive;
    }

    public void setCaptive(boolean captive) {
        this.captive = captive;
    }

    public int getIntellect() {
        return this.intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public int getFear() {
        return this.fear;
    }

    public void setFear(int fear) {
        this.fear = fear;
    }

    public enum Role {
        Captain, Helper, Robinzon, Sailor
    }

    public int calculateStrength(){
        return this.getPower() + this.getIntellect() - this.getFear();
    }

    public abstract void performAction();

    public abstract void defend();

    @Override
    public String toString() {
        return "Person(name = " + this.getName() + "intellect = " + this.getIntellect() + "fear = " + getFear() + "power = " + this.getPower() + "captive = " + this.getCaptive();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Person person = (Person) object;
        return Objects.equals(name, person.getName()) && power == person.getPower() && intellect == person.getIntellect();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, power, intellect);
    }

}
