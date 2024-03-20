package org.eci.auth;

import static spark.Spark.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * The AuthDB class provides functionality for managing user authentication using a database.
 * java -cp "target/classes;target/dependency/*" org.eci.auth.AuthDB
 */
public class AuthDB {

    /**
     * A HashMap representing the authentication database, where the keys are usernames and the values are encrypted passwords.
     */
    public static Map<String, String> authDB = new HashMap<>();

    /**
     * The main method to initialize the authentication database and start the server.
     *
     * @param args The command line arguments.
     * @throws NoSuchAlgorithmException If a requested cryptographic algorithm is not available in the environment.
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Populate the authentication database
        authDB.put("Sebas", Encoder.encodePass("Sebas123"));
        authDB.put("Tian", Encoder.encodePass("Tian123"));
        authDB.put("Rojas", Encoder.encodePass("Rojas123"));

        // Configure HTTPS with keystore
        secure("keystores/ecikeystore.p12", "123456", null, null);

        // Set port number
        port(getPort());

        // Define the login endpoint
        get("/login", (req, res) -> {
            String username = req.queryParams("user");
            String password = req.queryParams("pass");

            String response = checkUser(username, password);
            return response;
        });

    }

    /**
     * Checks if the provided username and password match the records in the authentication database.
     *
     * @param username The username to check.
     * @param password The password to check.
     * @return A message indicating the result of the login attempt.
     * @throws NoSuchAlgorithmException If a requested cryptographic algorithm is not available in the environment.
     */
    public static String checkUser(String username, String password) throws NoSuchAlgorithmException {
        if (!authDB.containsKey(username)) {
            return "Invalid username";
        } else {
            if (authDB.get(username).equals(Encoder.encodePass(password))) {
                return "Login successful";
            } else {
                return "Invalid password";
            }
        }
    }

    /**
     * Gets the port number from the environment variable or uses the default port if not set.
     *
     * @return The port number to listen on.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4400; // Default port
    }
}
