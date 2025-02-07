package others;

import errors.CaptiveException;

public interface MainCharacter {
    void talk(Person person) throws CaptiveException;

    void swim() throws CaptiveException;
}
