package nl.han.oose.dynamicroombackend.exception;

public class WrongParametersException extends RuntimeException {

    public WrongParametersException(String message) {
        super(message);
    }
}
