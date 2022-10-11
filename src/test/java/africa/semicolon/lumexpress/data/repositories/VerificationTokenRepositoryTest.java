package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.exception.VerificationTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VerificationTokenRepositoryTest {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    private VerificationToken verificationToken;

    @BeforeEach
    void setUp() {
        verificationTokenRepository.deleteAll();
        verificationToken = new VerificationToken();
        verificationToken.setToken("12345");
        verificationToken.setUserEmail("test@gmail.com");
        verificationTokenRepository.save(verificationToken);
    }

    @Test
    void findByUserEmail() throws VerificationTokenException {
        assertEquals(verificationToken.getToken(), verificationTokenRepository.findByUserEmail("test@gmail.com")
                .orElseThrow(VerificationTokenException::new).getToken());
    }

    @Test
    void findByToken() throws VerificationTokenException {
        assertEquals(verificationToken.getUserEmail(), verificationTokenRepository.findByToken("12345")
                .orElseThrow(VerificationTokenException::new).getUserEmail());
    }
}