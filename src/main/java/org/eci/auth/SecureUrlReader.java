package org.eci.auth;

import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * The SecureUrlReader class provides methods for securely reading content from a URL.
 */
public class SecureUrlReader {

    /**
     * Reads content from a secure URL using the provided username and password for authentication.
     *
     * @param username The username for authentication.
     * @param password The password for authentication.
     * @return The response from the URL.
     */
    public static String secureReadUrl(String username, String password) {
        String secureResponse = "";
        try {
            // Create a file and a password representation
            File trustStoreFile = new File("keystores/myTrustStore.p12");
            char[] trustStorePassword = "123456".toCharArray();

            // Load the trust store
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);

            // Initialize the TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);

            // Set the SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);

            // Read the secure URL
            secureResponse = readURL("https://localhost:4400/login?user=" + username + "&pass=" + password);

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException |
                 CertificateException | KeyManagementException ex) {
            Logger.getLogger(SecureUrlReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return secureResponse;
    }

    /**
     * Reads content from the specified URL.
     *
     * @param siteToRead The URL to read.
     * @return The content retrieved from the URL.
     */
    public static String readURL(String siteToRead) {
        StringBuilder response = new StringBuilder();
        try {
            // Create URL object
            URL siteURL = new URL(siteToRead);

            // Open connection to the URL
            URLConnection urlConnection = siteURL.openConnection();

            // Read from the connection
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                response.append(inputLine);
            }
            reader.close();
        } catch (IOException ex) {
            response.append(ex.getMessage());
        }
        return response.toString();
    }
}
