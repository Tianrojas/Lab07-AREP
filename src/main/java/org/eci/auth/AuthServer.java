/**
 * The AuthServer class provides functionality for setting up and running an authentication server.
 */
package org.eci.auth;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.secure;
import static spark.Spark.staticFiles;

//java -cp "target/classes;target/dependency/*" org.eci.auth.AuthServer
public class AuthServer {

    /**
     * The main method to start the authentication server.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // Configure HTTPS with keystore
        secure("keystores/ecikeystore.p12", "123456", null, null);

        // Set port number
        port(getPort());

        // Specify the location of static files
        staticFiles.location("/public");

        // Define the route for authentication
        get("/auth", (req, res) -> {
            String username = req.queryParams("user");
            String password = req.queryParams("pass");
            return SecureUrlReader.secureReadUrl(username, password);
        });
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
        return 4500; // Default port
    }
}
