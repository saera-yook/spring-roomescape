package roomescape.exception;

public class AlreadyExistingException extends IllegalArgumentException {
    public AlreadyExistingException(String message) {
        super(message);
    }
}
