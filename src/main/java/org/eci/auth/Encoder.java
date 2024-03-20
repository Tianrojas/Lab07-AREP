package org.eci.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The Encoder class provides functionality for encoding passwords using SHA-256 hashing algorithm.
 */
public class Encoder {

    /**
     * Encodes the given password using the SHA-256 hashing algorithm.
     *
     * @param password The password to encode.
     * @return The hashed password.
     * @throws NoSuchAlgorithmException If the requested cryptographic algorithm is not available in the environment.
     */
    public static String encodePass(String password) throws NoSuchAlgorithmException {

        // Create a MessageDigest instance with SHA-256 algorithm
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Generate the hash for the password bytes
        byte[] hash = md.digest(password.getBytes());

        // Convert the hash byte array to a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
