package nl.han.oose.dynamicroombackend.exception;

public class NonExistingWeekNumberException extends RuntimeException {

    public NonExistingWeekNumberException(String message) {
        super(message);
    }
}
