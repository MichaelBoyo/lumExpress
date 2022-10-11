package africa.semicolon.lumexpress.exception;

public class VerificationTokenException extends Exception{
    public VerificationTokenException(){
        super("verification token not found");
    }
}
