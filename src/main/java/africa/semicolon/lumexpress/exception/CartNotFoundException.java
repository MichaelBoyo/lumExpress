package africa.semicolon.lumexpress.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(){
        super("cart not found");
    }
}
