package src.practica1ejercicio1;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.SecureRandom;
import java.util.*;

/**
 * Class that implements a URL Shortener service
 * @author Guillermo Bernal y Miguel Reyna
 */
public class URLShortenerService {
    DoubleLinkedList<User> users = new DoubleLinkedList<>();
    DoubleLinkedList<URLEntry> looseURLs = new DoubleLinkedList<>();
    final private int ITERATIONS = 10000; // iterations used to create a random salt
    final private int KEY_LENGTH = 512; // key length for the random salt
    final private String URL_PREFIX = "https://fp.corti.to/";
    private final SecureRandom random = new SecureRandom();
    public URLShortenerService(){


    }
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
        String salt = bytesToHex(saltBytes);
        char[] passwordChars = password.toCharArray();
        byte[] hashedBytes = hashPassword(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);
        password = bytesToHex(hashedBytes);
        User usuarioAnadido = new User(username, password, salt);
        users.add(new DoubleNode<User>(usuarioAnadido));
    }
    /**
     * Converts a byte array into a hexadecimal string.
     *
     * @param bytes The byte array to convert
     * @return The resulting hexadecimal string
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0'); // Add a 0 if the hexadecimal value has a single character
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    /**
     * Converts a hexadecimal string to a byte array.
     *
     * @param hexString The hexadecimal string to convert
     * @return The resulting byte array
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
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password,salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded( );
            return res;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Searches for the position of an user in the [Users] list.
     *
     * @param username The username of the user we are searching.
     * @return An int containing the position of the user in the list.
     * @throws UserNotFoundException If the username provided does not exist.
     */
    private int getUserPosition(String username) throws UserNotFoundException {
        int i = 0;
        while (i < users.getSize() && !username.equals(users.get(i).getContent().getUsername())) {
            i++;
        }
        if (i >= users.getSize()) {
            throw new UserNotFoundException("¡Usuario no encontrado!");
        }
        return i;
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
    public boolean doLogin(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, UserNotFoundException {
        int i = getUserPosition(username);
        byte[] saltBytes = hexStringToBytes(users.get(i).getContent().getPasswordSalt());
        char[] passwordChars = password.toCharArray();
        byte[] hashedBytes = hashPassword(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);
        password = bytesToHex(hashedBytes);
        if(users.get(i).getContent().getPassword().equals(password)){
            return true;
        }
        else{
            System.out.println("¡Contraseña errónea!");
            return false;
        }
    }

    /**
     * Method that returns all user data by username
     *
     * In real life, this method would never be public. It is inly provided for testing purposes.
     *
     * @param username User's name
     * @return User representation
     */
    public User getUserData(String username) throws UserNotFoundException {
        return users.get(getUserPosition(username)).getContent();
    }

    /**
     * Method that returns a shortened URL in String format
     * @return shortened URL
     */
    private String shortenURLString(){
        String shortCode = generateRandomKey();
        return URL_PREFIX + shortCode;
    }

    /**
     * Method that generates a random key with the characters in [CHARACTERS]
     * @return Random [KEY_LENGTH_URL] length string
     */
    private String generateRandomKey() {
        int KEY_LENGTH_URL = 8;
        StringBuilder sb = new StringBuilder(KEY_LENGTH_URL);
        for (int i = 0; i < KEY_LENGTH_URL; i++) {
            String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    /**
     * Method that checks whether a longURL has already been converted
     * @param longURL the URL to check
     * @return The shortURL if found, null if not.
     */
    public String checkRepeatedURL(String longURL){
        int i = 0;
        while(i < looseURLs.getSize()){
            if(longURL.equals(looseURLs.get(i).getContent().getLongURL().toString())){
                return looseURLs.get(i).getContent().getLongURL().toString();
            }
            i++;
        }
        return null;
    }

    /**
     * Method that checks whether a longURL has already been converted
     * !!!THIS PROCEDURE IGNORES UPDATES IN THE EXPIRATION DATE
     * @param longURL the URL to check
     * @return The shortURL if found, null if not.
     */
    public String checkRepeatedURL(String longURL, String username) throws UserNotFoundException {
        int userPosition = getUserPosition(username);
        int i = 0;
        while(i < users.get(userPosition).getContent().getUrlEntries().getSize()){
            if(longURL.equals(users.get(userPosition).getContent().getUrlEntries().get(i).getContent().getLongURL().toString())){
                return users.get(userPosition).getContent().getUrlEntries().get(i).getContent().toString();
            }
            i++;
        }
        return null;
    }

    /**
     * Method that shortens a specified long URL and stores it for further retrieval
     * !!!this method should check whether the shortCode is already being used.
     *
     * @param longURL Long URL to shorten
     * @return The shortened URL
     * @throws IllegalStateException If generated short URL is already used.
     * @throws MalformedURLException If long URL is malformed
     */
    public String shortenURL(String longURL) throws IllegalStateException, MalformedURLException {
        //if the URL has already been shortened, return the same
        String checkedURL = checkRepeatedURL(longURL);
        if(checkedURL != null){
            return checkedURL;
        }else{
            String shortURL = shortenURLString();
            looseURLs.add(new DoubleNode<>(new URLEntry(new URL(shortURL), new URL(longURL), getDateInXDays())));
            return shortURL;
        }
    }
    /**
     * Method that returns the date in [DEFAULT_EXPIRATION_TIME] days
     * @return the date in [DEFAULT_EXPIRATION_TIME] days time
     */
    public Date getDateInXDays() {
        Calendar calendar = Calendar.getInstance();
        int DEFAULT_EXPIRATION_TIME = 30;
        calendar.add(Calendar.DAY_OF_MONTH, DEFAULT_EXPIRATION_TIME);
        return calendar.getTime();
    }
    /**
     * Method that creates a URLEntry from a longURL, a shortURL and an expirationDate
     * @param longURL Long URL to shorten
     * @param shortURL Shorted URL
     * @param expirationDate Date in which this URL expires and cannot be further returned by getOriginalURL() method
     * @return The URLEntry created
     */
    private URLEntry stringToURLEntry(String longURL, String shortURL, Date expirationDate) throws MalformedURLException {
        URL longURLtoURL = new URL(longURL);
        URL shortURLtoURL = new URL(shortURL);
        return new URLEntry(shortURLtoURL, longURLtoURL, expirationDate);
    }
    /**
     * Method that stores an URLEntry in a DLL inside the desired user
    * @param longURL Long URL to shorten
     * @param shortURL Shorted URL
    * @param username Username to authenticate and associate this URL to
    * @param expirationDate Date in which this URL expires and cannot be further returned by getOriginalURL() method
     */
    private void storeURLUser(String longURL, String shortURL, String username, Date expirationDate) throws MalformedURLException, UserNotFoundException {
        users.get(getUserPosition(username)).getContent().getUrlEntries().add(new DoubleNode<>(stringToURLEntry(longURL, shortURL, expirationDate)));
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
    public String shortenURL(String longURL, String username, String password) throws IllegalAccessException, NoSuchAlgorithmException, InvalidKeySpecException, MalformedURLException, UserNotFoundException {
        if(!doLogin(username, password)){
            throw new UserNotFoundException("Wrong username or password");
        }
        String checkedURL = checkRepeatedURL(longURL, username);
        if(checkedURL != null){
            return checkedURL;
        }else{
            String shortURL = shortenURLString();
            storeURLUser(longURL, shortURL, username, getDateInXDays());
            return shortURL;
        }
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
    public String shortenURL(String longURL, String username, String password, Date expirationDate) throws IllegalAccessException, NoSuchAlgorithmException, InvalidKeySpecException, MalformedURLException, UserNotFoundException {
        if(!doLogin(username, password)){
            throw new UserNotFoundException("Wrong username or password");
        }
        String checkedURL = checkRepeatedURL(longURL, username);
        if(checkedURL != null){
            return checkedURL;
        }else{
            String shortURL = shortenURLString();
            storeURLUser(longURL, shortURL, username, expirationDate);
            return shortURL;
        }
    }
    public boolean checkExpirationDate(URLEntry urlEntry){
        Date now = new Date();
        Date expirationDate = urlEntry.getExpirationTimestamp();
        if (expirationDate == null) {
            return true;
        }
        return expirationDate.after(now);
    }
    /**
     *
     * Retrieves a long URL from a Double Linked List
     *
     * @param shortURL The short URL to search for
     * @param URLEntries The DLL with URLs
     * @return the long URL linked to the short URL
     */
    private String retrieveLongURLFromDLL(String shortURL, DoubleLinkedList<URLEntry> URLEntries) throws URLNotFoundException, URLExpiredException {
        int i = 0;
        while (i < URLEntries.getSize()) {
            URLEntry entry = URLEntries.get(i).getContent();
            if (shortURL.equals(entry.getShortURL())) {
                if(checkExpirationDate(entry)){
                    return entry.getLongURL().toString();
                }else{
                    throw new URLExpiredException("La URL ha caducado");
                }
            }
            i++;
        }
        throw new URLNotFoundException("No se ha encontrado la URL indicada");
    }
    /**
     * Gets the original (long) URL associated to a short URL.
     * If the URL is expired, an error message is returned.
     * If the URL is not found, an error message is returned.
     *
     * @param shortURL The short URL to search for
     * @return The long URL linked to this short URL
     */
    public String getOriginalURL(String shortURL) throws URLNotFoundException, URLExpiredException {
        return retrieveLongURLFromDLL(shortURL, looseURLs);
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
    public String getOriginalURL(String shortURL, String username, String password) throws IllegalAccessException, NoSuchAlgorithmException, InvalidKeySpecException, UserNotFoundException, URLNotFoundException, URLExpiredException {
        if(!doLogin(username, password)){
            throw new UserNotFoundException("Wrong username or password");
        }
        return retrieveLongURLFromDLL(shortURL, users.get(getUserPosition(username)).getContent().getUrlEntries());
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
    public String[] getURLsByUsername(String username, String password) throws IllegalAccessException, NoSuchAlgorithmException, InvalidKeySpecException, UserNotFoundException {
        if (!doLogin(username, password)) {
            throw new UserNotFoundException("Wrong username or password");
        }
        DoubleLinkedList<URLEntry> urlList = getUserData(username).getUrlEntries();
        //A temporal array is used to store the data while the validCount is calculated
        String[] tempURLs = new String[urlList.getSize()];
        int validCount = 0;
        for (int i = 0; i < urlList.getSize(); i++) {
            if (checkExpirationDate(urlList.get(i).getContent())) {
                tempURLs[validCount] = urlList.get(i).getContent().getShortURL().toString();
                validCount++;
            }
        }
        //After we know the real length of the array, we copy the data in an array with the right size
        String[] URLs = new String[validCount];
        System.arraycopy(tempURLs, 0, URLs, 0, validCount);
        return URLs;
    }
    public DoubleLinkedList<URLEntry> getLooseURLs() {
        return looseURLs;
    }
}
