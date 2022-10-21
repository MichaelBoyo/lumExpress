package africa.semicolon.lumexpress.service.verificationTokenService;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.exception.VerificationTokenException;

public interface VerificationTokenService {
    VerificationToken createToken(String email);

    boolean isValidVerificationToken(String token) throws VerificationTokenException;
}
