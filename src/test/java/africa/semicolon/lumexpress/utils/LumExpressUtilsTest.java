package africa.semicolon.lumexpress.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LumExpressUtilsTest {

    @Test
    void generateRandomString() {
        String randomString = LumExpressUtils.generateToken();
        assertEquals(5, randomString.length());
        assertNotNull(randomString);
    }
    @Test
    void nonsense(){
        String randomString = " 1 2345";
        randomString = randomString.replace("1", "a");
        assertThat(randomString).isEqualTo(" a 2345");

    }
}