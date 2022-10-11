package africa.semicolon.lumexpress.exception;

public class VerificationTokenException extends RuntimeException {
    public VerificationTokenException() {
        super("token is invalid");
    }
}
