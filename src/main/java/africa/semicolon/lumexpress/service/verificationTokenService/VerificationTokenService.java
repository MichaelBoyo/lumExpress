package africa.semicolon.lumexpress.service.verificationTokenService;

import africa.semicolon.lumexpress.data.models.VerificationToken;

public interface VerificationTokenService {
    VerificationToken createToken(String email);

    boolean isValidVerificationToken(String token);
}
