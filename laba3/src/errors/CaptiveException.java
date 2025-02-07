package errors;

import others.Person;

public class CaptiveException extends Exception {
    public CaptiveException(Person person) {
        super(person.getName() + " попал в плен");
    }
}
