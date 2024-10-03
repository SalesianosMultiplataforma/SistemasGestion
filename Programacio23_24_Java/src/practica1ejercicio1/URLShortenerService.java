package src.practica1ejercicio1;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.SecureRandom;
import java.util.*;

/**
 * Class that implements a URL Shortener service
 */
public class URLShortenerService {

    // TODO: Implement this class and its methods

    /**
     * Adds a user identified by a username and a password
     * Once it is added, it can be authenticated to other methods with the required credentials
     *
     * This method must generate a different password salt for each distinct user, and store it accordingly
     * The password field CANNOT BE STORED in plaintext. It must be hashed according to specifications.
     *
     * @param username Username to authenticate and associate this URL to
     * @param password User's password
     * @throws NoSuchAlgorithmException If hashing algorithm is not found
     * @throws InvalidKeySpecException If hashing key exception
     */
    public void addUser(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] saltBytes = generateSalt(16);
        String saltString = bytesToHex(saltBytes);

        int iterations = 10000;
        int keyLength = 512;
        char[] passwordChars = password.toCharArray();
        byte[] hashedBytes = hashPassword(passwordChars, saltBytes, iterations, keyLength);
        String hashedString = bytesToHex(hashedBytes);

    }
    /**
     * Convierte un array de bytes en una cadena hexadecimal.
     *
     * @param bytes Array de bytes a convertir
     * @return Cadena hexadecimal resultante
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0'); // Añadir un 0 si el valor hexadecimal es de un solo carácter
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    /**
     * Convierte una cadena hexadecimal en un array de bytes.
     *
     * @param hexString Cadena hexadecimal a convertir
     * @return Array de bytes resultante
     */
    public static byte[] hexStringToBytes(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }
    /**
     * Generates a unique salt using a cryptographically secure random number generator.
     *
     * @param length The desired length of the salt in bytes.
     * @return A byte array containing the randomly generated salt.
     * @throws NoSuchAlgorithmException If the algorithm used for random number generation is not available.
     */
    private byte[] generateSalt(int length) throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[length];
        sr.nextBytes(salt);
        return salt;
    }
    /**
     * Hashes the user's password using the PBKDF2WithHmacSHA512 algorithm.
     * It takes the password, a salt, the number of iterations, and the desired key length to produce a secure hash.
     *
     * @param password   The user's password in character array form.
     * @param salt       A unique salt for this password.
     * @param iterations The number of iterations used in the PBKDF2 algorithm.
     * @param keyLength  The desired length of the derived key (hash).
     * @return A byte array containing the securely hashed password.
     */
    public byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Helper method to authenticate an already registered user
     *
     * @param username Username to authenticate and associate this URL to
     * @param password User's password
     * @return True if user is correctly authenticated; false otherwise
     * @throws NoSuchAlgorithmException If hashing algorithm is not found
     * @throws InvalidKeySpecException If hashing key exception
     */
    public boolean doLogin(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return false;
    }

    /**
     * Method that returns all user data by username
     *
     * In real life, this method would never be public. It is inly provided for testing purposes.
     *
     * @param username User's name
     * @return User representation
     */
    public User getUserData(String username) {
        return null;
    }

    /**
     * Method that shortens a specified long URL and stores it for further retrieval
     *
     * @param longURL Long URL to shorten
     * @return The shortened URL
     * @throws IllegalStateException If generated short URL is already used.
     * @throws MalformedURLException If long URL is malformed
     */
    public String shortenURL(String longURL) throws IllegalStateException, MalformedURLException {
        return "https://zaragoza.salesianos.edu";
    }

    /**
     * Method that shortens a specified long URL and stores it alongside the user
     * This method requires to authenticate the provided username and password.
     *
     * @param longURL Long URL to shorten
     * @param username Username to authenticate and associate this URL to
     * @param password User's password
     * @return The shortened URL
     * @throws IllegalAccessException If user credentials are invalid
     * @throws NoSuchAlgorithmException If hashing algorithm is not found
     * @throws InvalidKeySpecException If hashing key exception
     * @throws MalformedURLException If long URL is malformed
     */
    public String shortenURL(String longURL, String username, String password) throws IllegalAccessException, NoSuchAlgorithmException, InvalidKeySpecException, MalformedURLException {
        return "https://zaragoza.salesianos.edu";
    }

    /**
     * Method that shortens a specified long URL and stores it alongside the user, as well as its expiration date
     * This method requires to authenticate the provided username and password.
     *
     * @param longURL Long URL to shorten
     * @param username Username to authenticate and associate this URL to
     * @param password User's password
     * @param expirationDate Date in which this URL expires and cannot be further returned by getOriginalURL() method
     * @return The shortened URL
     * @throws IllegalAccessException If user credentials are invalid
     * @throws NoSuchAlgorithmException If hashing algorithm is not found
     * @throws InvalidKeySpecException If hashing key exception
     * @throws MalformedURLException If long URL is malformed
     */
    public String shortenURL(String longURL, String username, String password, Date expirationDate) throws IllegalAccessException, NoSuchAlgorithmException, InvalidKeySpecException, MalformedURLException {
        return "https://zaragoza.salesianos.edu";
    }

    /**
     * Gets the original (long) URL associated to a short URL.
     * If the URL is expired, an error message is returned.
     * If the URL is not found, an error message is returned.
     *
     * @param shortURL The short URL to search for
     * @return The long URL linked to this short URL
     */
    public String getOriginalURL(String shortURL) {
        return "https://zaragoza.salesianos.edu";
    }

    /**
     * Gets the original (long) URL associated to a short URL.
     * If the URL is expired, an error message is returned.
     * If the URL is not found, an error message is returned.
     * This method requires to authenticate the provided username and password.
     *
     * @param shortURL The short URL to search for
     * @param username Username to authenticate and associate this URL to
     * @param password User's password
     * @return The long URL linked to this short URL
     * @throws IllegalAccessException If user credentials are invalid
     * @throws NoSuchAlgorithmException If hashing algorithm is not found
     * @throws InvalidKeySpecException If hashing key exception
     */
    public String getOriginalURL(String shortURL, String username, String password) throws IllegalAccessException, NoSuchAlgorithmException, InvalidKeySpecException {
        return "https://zaragoza.salesianos.edu";
    }

    /**
     * Gets all the URLs linked to a specific user
     * This method requires to authenticate the provided username and password.
     *
     * @param username Username to authenticate and associate this URL to
     * @param password User's password
     * @return An array with all URLs this user has in store
     * @throws IllegalAccessException If user credentials are invalid
     * @throws NoSuchAlgorithmException If hashing algorithm is not found
     * @throws InvalidKeySpecException If hashing key exception
     */
    public String[] getURLsByUsername(String username, String password) throws IllegalAccessException, NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
