package africa.semicolon.lumexpress.exception;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(){
        super("cart not found");
    }
}
