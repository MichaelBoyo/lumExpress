package africa.semicolon.lumexpress.service.verificationTokenService;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.data.repositories.VerificationTokenRepository;
import africa.semicolon.lumexpress.exception.VerificationTokenException;
import africa.semicolon.lumexpress.utils.LumExpressUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken createToken(String email) {
        String token = LumExpressUtils.generateToken();
        return verificationTokenRepository.save(VerificationToken.builder()
                .token(token)
                .userEmail(email)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .build());
    }

    @Override
    public boolean isValidVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token)
                .map(foundToken -> foundToken.getExpiresAt().isAfter(LocalDateTime.now()))
                .orElseThrow(VerificationTokenException::new);
    }
}
