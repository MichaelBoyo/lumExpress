package africa.semicolon.lumexpress.exception;

public class VerificationTokenException extends Exception {
    public VerificationTokenException() {
        super("token is invalid");
    }
}
