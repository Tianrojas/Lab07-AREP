import java.security.NoSuchAlgorithmException;
import junit.framework.TestCase;
import org.eci.auth.AuthDB;
import org.eci.auth.Encoder;

/**
 * The AuthTest class provides unit tests for the AuthDB class.
 */
public class AuthTest extends TestCase {
    private AuthDB auth = new AuthDB();

    /**
     * Initializes the authentication database with test users and passwords.
     *
     * @throws NoSuchAlgorithmException If a requested cryptographic algorithm is not available in the environment.
     */
    public void setUp() throws NoSuchAlgorithmException {
        auth.authDB.put("test1", Encoder.encodePass("test123"));
        auth.authDB.put("test2", Encoder.encodePass("test234"));
    }

    /**
     * Tests the behavior when an invalid username is provided.
     *
     * @throws NoSuchAlgorithmException If a requested cryptographic algorithm is not available in the environment.
     */
    public void testInvalidUsername() throws NoSuchAlgorithmException {
        assertEquals("Invalid username", auth.checkUser("testN", "123456"));
    }

    /**
     * Tests the behavior when a valid username and password are provided.
     *
     * @throws NoSuchAlgorithmException If a requested cryptographic algorithm is not available in the environment.
     */
    public void testValidLogin() throws NoSuchAlgorithmException {
        assertEquals("Login successful", auth.checkUser("test1", "test123"));
    }

    /**
     * Tests the behavior when an invalid password is provided.
     *
     * @throws NoSuchAlgorithmException If a requested cryptographic algorithm is not available in the environment.
     */
    public void testInvalidPassword() throws NoSuchAlgorithmException {
        assertEquals("Invalid password", auth.checkUser("test2", "test123"));
    }
}
