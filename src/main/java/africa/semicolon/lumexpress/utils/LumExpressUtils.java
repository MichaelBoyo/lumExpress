package africa.semicolon.lumexpress.utils;


import java.security.SecureRandom;

public class LumExpressUtils {
    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        int token = 10000 + random.nextInt(89000) ;
        return String.valueOf(token);
    }

}
